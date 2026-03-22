package br.edu.ifbasaj.xadrez.domain.model;

import java.util.List;
import java.util.Optional;

public class Tabuleiro {

    private final Peca[][] casas;

    public Tabuleiro() {
        this.casas = new Peca[8][8];
    }

    public Optional<Peca> getPeca(Posicao posicao) {
        return Optional.ofNullable(casas[posicao.getLinha()][posicao.getColuna()]);
    }

    public void colocarPeca(Peca peca, Posicao posicao) {
        casas[posicao.getLinha()][posicao.getColuna()] = peca;
    }

    public void removerPeca(Posicao posicao) {
        casas[posicao.getLinha()][posicao.getColuna()] = null;
    }

    public boolean estaDentroDoLimite(int linha, int coluna) {
        return linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8;
    }

    public Peca[][] getMatriz() {
        return casas;
    }

    /**
     * Move a peça de origem para destino e devolve a peça capturada (se houver).
     */
    public Peca mover(Posicao origem, Posicao destino) {
        Peca pecaOrigem = casas[origem.getLinha()][origem.getColuna()];
        if (pecaOrigem == null) {
            throw new IllegalArgumentException("Não há peça na casa de origem.");
        }

        Peca capturada = casas[destino.getLinha()][destino.getColuna()];

        casas[destino.getLinha()][destino.getColuna()] = pecaOrigem;
        casas[origem.getLinha()][origem.getColuna()] = null;

        return capturada;
    }

    /**
     * Encontra a posição do rei de uma determinada cor.
     */
    public Posicao encontrarPosicaoRei(Cor cor) {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = casas[linha][coluna];
                if (peca != null &&
                        peca.getTipo() == TipoPeca.REI &&
                        peca.getCor() == cor) {
                    return new Posicao(linha, coluna);
                }
            }
        }
        return null; // não achou (não deveria acontecer em jogo normal)
    }

    /**
     * Verifica se o rei da cor passada está em xeque.
     */
    public boolean estaEmXeque(Cor corRei) {
        Posicao posicaoRei = encontrarPosicaoRei(corRei);
        if (posicaoRei == null) {
            return false;
        }

        Cor corOponente = (corRei == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = casas[linha][coluna];
                if (peca != null && peca.getCor() == corOponente) {
                    Posicao posAtual = new Posicao(linha, coluna);
                    List<Posicao> movimentos = peca.movimentosPossiveis(this, posAtual);
                    if (movimentos.contains(posicaoRei)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
