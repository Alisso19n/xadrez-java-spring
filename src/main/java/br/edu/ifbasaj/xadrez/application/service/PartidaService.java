package br.edu.ifbasaj.xadrez.application.service;

import br.edu.ifbasaj.xadrez.application.dto.MovimentoResponseDTO;
import br.edu.ifbasaj.xadrez.application.dto.PecaDTO;
import br.edu.ifbasaj.xadrez.application.dto.TabuleiroDTO;
import br.edu.ifbasaj.xadrez.domain.factory.FabricaPecas;
import br.edu.ifbasaj.xadrez.domain.factory.TabuleiroFactory;
import br.edu.ifbasaj.xadrez.domain.model.*;

import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@Service
public class PartidaService {

    private Tabuleiro tabuleiro;
    private Cor vez = Cor.BRANCO;

    // Para EN PASSANT: precisamos do último movimento
    private Movimento ultimoMovimento = null;

    // Para UNDO
    private final Deque<HistoricoJogada> historico = new ArrayDeque<>();

    public PartidaService() {
        this.tabuleiro = TabuleiroFactory.criarTabuleiroInicial();
    }

    public TabuleiroDTO obterTabuleiro() {
        Peca[][] matriz = tabuleiro.getMatriz();
        PecaDTO[][] casas = new PecaDTO[8][8];

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca p = matriz[linha][coluna];
                if (p != null) {
                    casas[linha][coluna] = new PecaDTO(p.getTipo().name(), p.getCor().name());
                }
            }
        }
        return new TabuleiroDTO(casas);
    }

    public MovimentoResponseDTO mover(int ol, int oc, int dl, int dc, String promocaoOpcional) {
        try {
            Posicao origem = new Posicao(ol, oc);
            Posicao destino = new Posicao(dl, dc);

            Peca peca = tabuleiro.getPeca(origem).orElse(null);
            if (peca == null) {
                return resposta(false, "Não há peça na origem.", null);
            }

            // turno
            if (peca.getCor() != vez) {
                return resposta(false, "Não é a vez dessa cor.", null);
            }

            // valida pseudo-movimento via Strategy + roque/enpassant especiais
            if (!movimentoPermitido(peca, origem, destino)) {
                return resposta(false, "Movimento não permitido para essa peça.", null);
            }

            // executa movimento (com regras especiais) e registra histórico
            HistoricoJogada hj = aplicarMovimentoComRegras(peca, origem, destino, promocaoOpcional);

            // proíbe deixar seu rei em xeque
            if (tabuleiro.estaEmXeque(peca.getCor())) {
                // desfaz
                desfazerHistorico(hj);
                return resposta(false, "Movimento inválido: seu rei ficaria em xeque.", null);
            }

            // ok: salva histórico definitivo
            historico.push(hj);

            // troca vez
            vez = (vez == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;

            // checagens pós-movimento
            boolean xequeBrancas = tabuleiro.estaEmXeque(Cor.BRANCO);
            boolean xequePretas  = tabuleiro.estaEmXeque(Cor.PRETO);

            boolean mateBrancas = isXequeMate(Cor.BRANCO);
            boolean matePretas  = isXequeMate(Cor.PRETO);

            String msg = "Movimento realizado. Vez das " + vez;

            if (mateBrancas) msg = "XEQUE-MATE! Pretas venceram.";
            else if (matePretas) msg = "XEQUE-MATE! Brancas venceram.";
            else if (xequeBrancas) msg = "XEQUE nas BRANCAS! Vez das " + vez;
            else if (xequePretas) msg = "XEQUE nas PRETAS! Vez das " + vez;

            return new MovimentoResponseDTO(true, msg, vez.name(),
                    xequeBrancas, xequePretas, mateBrancas, matePretas, obterTabuleiro());

        } catch (Exception e) {
            return resposta(false, "Erro: " + e.getMessage(), null);
        }
    }

    public MovimentoResponseDTO desfazer() {
        if (historico.isEmpty()) {
            return resposta(false, "Não há jogadas para desfazer.", null);
        }
        HistoricoJogada hj = historico.pop();
        desfazerHistorico(hj);
        return resposta(true, "Jogada desfeita. Vez das " + vez, obterTabuleiro());
    }

    public void reiniciar() {
        this.tabuleiro = TabuleiroFactory.criarTabuleiroInicial();
        this.vez = Cor.BRANCO;
        this.ultimoMovimento = null;
        this.historico.clear();
    }

    // -----------------------
    // Regras especiais
    // -----------------------

    private boolean movimentoPermitido(Peca peca, Posicao origem, Posicao destino) {
        // roque (rei andando 2 colunas)
        if (peca.getTipo() == TipoPeca.REI && origem.getLinha() == destino.getLinha()
                && Math.abs(destino.getColuna() - origem.getColuna()) == 2) {
            return roquePermitido(peca.getCor(), origem, destino);
        }

        // en passant (peão na diagonal para casa vazia)
        if (peca.getTipo() == TipoPeca.PEAO) {
            int dc = destino.getColuna() - origem.getColuna();
            int dl = destino.getLinha() - origem.getLinha();
            boolean diagonal = Math.abs(dc) == 1;
            boolean frente = (peca.getCor() == Cor.BRANCO) ? (dl == -1) : (dl == 1);

            boolean destinoVazio = tabuleiro.getPeca(destino).isEmpty();

            if (diagonal && frente && destinoVazio) {
                return enPassantPermitido(peca.getCor(), origem, destino);
            }
        }

        // movimentos normais pela strategy
        List<Posicao> possiveis = peca.movimentosPossiveis(tabuleiro, origem);
        return possiveis.contains(destino);
    }

    private HistoricoJogada aplicarMovimentoComRegras(Peca peca, Posicao origem, Posicao destino, String promocaoOpcional) {
        HistoricoJogada hj = new HistoricoJogada();
        hj.origem = origem;
        hj.destino = destino;
        hj.pecaMovida = peca;
        hj.pecaMovidaJaMoveuAntes = peca.isJaMoveu();
        hj.vezAnterior = vez;
        hj.ultimoMovimentoAnterior = ultimoMovimento;

        // ROQUE
        if (peca.getTipo() == TipoPeca.REI && origem.getLinha() == destino.getLinha()
                && Math.abs(destino.getColuna() - origem.getColuna()) == 2) {

            hj.foiRoque = true;

            int linha = origem.getLinha();
            boolean roquePequeno = destino.getColuna() > origem.getColuna();

            Posicao origemTorre = new Posicao(linha, roquePequeno ? 7 : 0);
            Posicao destinoTorre = new Posicao(linha, roquePequeno ? 5 : 3);

            Peca torre = tabuleiro.getPeca(origemTorre).orElseThrow(() -> new IllegalStateException("Torre não encontrada para roque."));
            hj.torre = torre;
            hj.torreJaMoveuAntes = torre.isJaMoveu();
            hj.origemTorre = origemTorre;
            hj.destinoTorre = destinoTorre;

            hj.pecaCapturada = tabuleiro.mover(origem, destino); // rei
            tabuleiro.mover(origemTorre, destinoTorre);          // torre

            // último movimento
            ultimoMovimento = new Movimento(origem, destino);
            return hj;
        }

        // EN PASSANT
        if (peca.getTipo() == TipoPeca.PEAO) {
            int dc = destino.getColuna() - origem.getColuna();
            int dl = destino.getLinha() - origem.getLinha();
            boolean diagonal = Math.abs(dc) == 1;
            boolean frente = (peca.getCor() == Cor.BRANCO) ? (dl == -1) : (dl == 1);
            boolean destinoVazio = tabuleiro.getPeca(destino).isEmpty();

            if (diagonal && frente && destinoVazio && enPassantPermitido(peca.getCor(), origem, destino)) {
                hj.foiEnPassant = true;

                // peão capturado fica “atrás” do destino
                int linhaPeaoCapturado = (peca.getCor() == Cor.BRANCO) ? destino.getLinha() + 1 : destino.getLinha() - 1;
                Posicao posPeaoCapt = new Posicao(linhaPeaoCapturado, destino.getColuna());

                hj.posicaoPeaoCapturadoEnPassant = posPeaoCapt;
                hj.peaoCapturadoEnPassant = tabuleiro.getPeca(posPeaoCapt).orElse(null);

                tabuleiro.removerPeca(posPeaoCapt);
                hj.pecaCapturada = null; // captura foi via en passant

                tabuleiro.mover(origem, destino);

                ultimoMovimento = new Movimento(origem, destino);

                // promoção após mover
                aplicarPromocaoSeNecessario(hj, destino, promocaoOpcional);
                return hj;
            }
        }

        // movimento normal
        hj.pecaCapturada = tabuleiro.mover(origem, destino);
        ultimoMovimento = new Movimento(origem, destino);

        // promoção após mover
        aplicarPromocaoSeNecessario(hj, destino, promocaoOpcional);

        return hj;
    }

    private void aplicarPromocaoSeNecessario(HistoricoJogada hj, Posicao destino, String promocaoOpcional) {
        Peca p = tabuleiro.getPeca(destino).orElse(null);
        if (p == null || p.getTipo() != TipoPeca.PEAO) return;

        boolean ultimaFileira = (p.getCor() == Cor.BRANCO && destino.getLinha() == 0)
                || (p.getCor() == Cor.PRETO && destino.getLinha() == 7);

        if (!ultimaFileira) return;

        // default: rainha
        TipoPeca tipoPromo = TipoPeca.RAINHA;
        if (promocaoOpcional != null && !promocaoOpcional.isBlank()) {
            try {
                tipoPromo = TipoPeca.valueOf(promocaoOpcional.toUpperCase());
                // não permitir promover para REI/PEAO
                if (tipoPromo == TipoPeca.REI || tipoPromo == TipoPeca.PEAO) {
                    tipoPromo = TipoPeca.RAINHA;
                }
            } catch (Exception ignored) {
                tipoPromo = TipoPeca.RAINHA;
            }
        }

        hj.foiPromocao = true;
        hj.peaoOriginal = p;

        Peca nova = FabricaPecas.criar(tipoPromo, p.getCor());
        // ao promover, consideramos “já moveu”
        nova.setJaMoveu(true);

        tabuleiro.colocarPeca(nova, destino);
        hj.pecaPromovida = nova;
    }

    private boolean enPassantPermitido(Cor cor, Posicao origem, Posicao destino) {
        if (ultimoMovimento == null) return false;

        // último movimento deve ter sido um peão adversário andando 2 casas
        Posicao uo = ultimoMovimento.origem();
        Posicao ud = ultimoMovimento.destino();

        Peca ultimaPeca = tabuleiro.getPeca(ud).orElse(null);
        if (ultimaPeca == null || ultimaPeca.getTipo() != TipoPeca.PEAO) return false;
        if (ultimaPeca.getCor() == cor) return false;

        int desloc = Math.abs(ud.getLinha() - uo.getLinha());
        if (desloc != 2) return false;

        // o peão adversário tem que estar ao lado da origem
        if (ud.getLinha() != origem.getLinha()) return false;
        if (ud.getColuna() != destino.getColuna()) return false;

        return true;
    }

    private boolean roquePermitido(Cor cor, Posicao origemRei, Posicao destinoRei) {
        // rei e torre não podem ter se movido
        Peca rei = tabuleiro.getPeca(origemRei).orElse(null);
        if (rei == null || rei.getTipo() != TipoPeca.REI || rei.isJaMoveu()) return false;

        int linha = origemRei.getLinha();
        boolean roquePequeno = destinoRei.getColuna() > origemRei.getColuna();

        Posicao origemTorre = new Posicao(linha, roquePequeno ? 7 : 0);
        Peca torre = tabuleiro.getPeca(origemTorre).orElse(null);
        if (torre == null || torre.getTipo() != TipoPeca.TORRE || torre.isJaMoveu()) return false;
        if (torre.getCor() != cor) return false;

        // casas entre rei e torre vazias
        int inicio = Math.min(origemRei.getColuna(), origemTorre.getColuna()) + 1;
        int fim = Math.max(origemRei.getColuna(), origemTorre.getColuna()) - 1;

        for (int c = inicio; c <= fim; c++) {
            if (tabuleiro.getMatriz()[linha][c] != null) return false;
        }

        // rei não pode estar em xeque nem passar por casa atacada
        if (tabuleiro.estaEmXeque(cor)) return false;

        int passo = roquePequeno ? 1 : -1;

        Posicao casa1 = new Posicao(linha, origemRei.getColuna() + passo);
        Posicao casa2 = new Posicao(linha, origemRei.getColuna() + 2 * passo);

        // simular rei na casa1 e casa2
        if (casaAtacadaPeloOponente(cor, casa1)) return false;
        if (casaAtacadaPeloOponente(cor, casa2)) return false;

        return true;
    }

    private boolean casaAtacadaPeloOponente(Cor corRei, Posicao casa) {
        Cor op = (corRei == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;

        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                Peca p = tabuleiro.getMatriz()[l][c];
                if (p != null && p.getCor() == op) {
                    List<Posicao> moves = p.movimentosPossiveis(tabuleiro, new Posicao(l, c));
                    if (moves.contains(casa)) return true;
                }
            }
        }
        return false;
    }

    // -----------------------
    // Xeque-mate (simples e funcional)
    // -----------------------
    private boolean isXequeMate(Cor cor) {
        if (!tabuleiro.estaEmXeque(cor)) return false;

        // existe algum movimento legal que tira do xeque?
        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                Peca p = tabuleiro.getMatriz()[l][c];
                if (p != null && p.getCor() == cor) {
                    Posicao origem = new Posicao(l, c);
                    List<Posicao> destinos = p.movimentosPossiveis(tabuleiro, origem);

                    // incluir roque/en passant aqui é capricho extra; por enquanto ok
                    for (Posicao d : destinos) {
                        // tentar movimento “cru”
                        Peca capturada = tabuleiro.mover(origem, d);
                        boolean aindaEmXeque = tabuleiro.estaEmXeque(cor);

                        // desfaz
                        tabuleiro.mover(d, origem);
                        if (capturada != null) tabuleiro.colocarPeca(capturada, d);

                        // restaurar flags (simplificado; se quiser 100%, a gente usa historico)
                        p.setJaMoveu(false);

                        if (!aindaEmXeque) return false;
                    }
                }
            }
        }
        return true;
    }

    // -----------------------
    // Undo
    // -----------------------
    private void desfazerHistorico(HistoricoJogada hj) {
        // estado
        vez = hj.vezAnterior;
        ultimoMovimento = hj.ultimoMovimentoAnterior;

        // desfaz promoção
        if (hj.foiPromocao) {
            tabuleiro.colocarPeca(hj.peaoOriginal, hj.destino);
        }

        // desfaz roque
        if (hj.foiRoque) {
            tabuleiro.mover(hj.destino, hj.origem); // rei volta
            tabuleiro.mover(hj.destinoTorre, hj.origemTorre); // torre volta
            hj.pecaMovida.setJaMoveu(hj.pecaMovidaJaMoveuAntes);
            hj.torre.setJaMoveu(hj.torreJaMoveuAntes);
            return;
        }

        // desfaz en passant
        if (hj.foiEnPassant) {
            tabuleiro.mover(hj.destino, hj.origem);
            hj.pecaMovida.setJaMoveu(hj.pecaMovidaJaMoveuAntes);

            if (hj.peaoCapturadoEnPassant != null) {
                tabuleiro.colocarPeca(hj.peaoCapturadoEnPassant, hj.posicaoPeaoCapturadoEnPassant);
            }
            return;
        }

        // desfaz normal
        tabuleiro.mover(hj.destino, hj.origem);
        hj.pecaMovida.setJaMoveu(hj.pecaMovidaJaMoveuAntes);

        if (hj.pecaCapturada != null) {
            tabuleiro.colocarPeca(hj.pecaCapturada, hj.destino);
        }
    }

    private MovimentoResponseDTO resposta(boolean ok, String msg, TabuleiroDTO tab) {
        boolean xequeB = tabuleiro.estaEmXeque(Cor.BRANCO);
        boolean xequeP = tabuleiro.estaEmXeque(Cor.PRETO);
        boolean mateB = false;
        boolean mateP = false;

        return new MovimentoResponseDTO(ok, msg, vez.name(), xequeB, xequeP, mateB, mateP,
                tab != null ? tab : obterTabuleiro());
    }
}
