package br.edu.ifbasaj.xadrez.domain.model;

import java.util.Objects;

/**
 * Representa uma posição no tabuleiro 8x8 (linha e coluna de 0 a 7).
 */
public class Posicao {

    private final int linha;
    private final int coluna;

    public Posicao(int linha, int coluna) {
        if (linha < 0 || linha > 7 || coluna < 0 || coluna > 7) {
            throw new IllegalArgumentException("Posicao invalida: (" + linha + ", " + coluna + ")");
        }
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Posicao posicao)) return false;
        return linha == posicao.linha && coluna == posicao.coluna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(linha, coluna);
    }

    @Override
    public String toString() {
        return "(" + linha + "," + coluna + ")";
    }
}
