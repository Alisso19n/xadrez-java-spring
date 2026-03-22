package br.edu.ifbasaj.xadrez.domain.strategy;

import br.edu.ifbasaj.xadrez.domain.model.Cor;
import br.edu.ifbasaj.xadrez.domain.model.Peca;
import br.edu.ifbasaj.xadrez.domain.model.Posicao;
import br.edu.ifbasaj.xadrez.domain.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovimentoPeao implements MovimentoStrategy {

    @Override
    public List<Posicao> calcularMovimentosValidos(Tabuleiro tabuleiro, Posicao posicaoAtual) {
        List<Posicao> movimentos = new ArrayList<>();

        Optional<Peca> pecaOpt = tabuleiro.getPeca(posicaoAtual);
        if (pecaOpt.isEmpty()) return movimentos;

        Peca peca = pecaOpt.get();
        Cor cor = peca.getCor();

        int direcao = (cor == Cor.BRANCO) ? -1 : 1; // branco sobe, preto desce
        int linhaAtual = posicaoAtual.getLinha();
        int colunaAtual = posicaoAtual.getColuna();

        // 1 casa pra frente
        int novaLinha = linhaAtual + direcao;
        if (tabuleiro.estaDentroDoLimite(novaLinha, colunaAtual)) {
            Posicao frente = new Posicao(novaLinha, colunaAtual);
            // Só anda se estiver vazia
            if (tabuleiro.getPeca(frente).isEmpty()) {
                movimentos.add(frente);

                // 2 casas pra frente se for posição inicial e caminho livre
                boolean naPosicaoInicialBranco = (cor == Cor.BRANCO && linhaAtual == 6);
                boolean naPosicaoInicialPreto = (cor == Cor.PRETO && linhaAtual == 1);
                if (naPosicaoInicialBranco || naPosicaoInicialPreto) {
                    int duasCasasLinha = linhaAtual + 2 * direcao;
                    if (tabuleiro.estaDentroDoLimite(duasCasasLinha, colunaAtual)) {
                        Posicao duasFrente = new Posicao(duasCasasLinha, colunaAtual);
                        if (tabuleiro.getPeca(duasFrente).isEmpty()) {
                            movimentos.add(duasFrente);
                        }
                    }
                }
            }
        }

        // Capturas diagonais
        int[][] capturas = {
                {direcao, -1},
                {direcao, 1}
        };

        for (int[] cap : capturas) {
            int linhaCap = linhaAtual + cap[0];
            int colunaCap = colunaAtual + cap[1];

            if (!tabuleiro.estaDentroDoLimite(linhaCap, colunaCap)) continue;

            Posicao posCap = new Posicao(linhaCap, colunaCap);
            Optional<Peca> pecaDestino = tabuleiro.getPeca(posCap);

            if (pecaDestino.isPresent() && pecaDestino.get().getCor() != cor) {
                movimentos.add(posCap);
            }
        }

        // (sem en passant nem promoção por enquanto)
        return movimentos;
    }
}
