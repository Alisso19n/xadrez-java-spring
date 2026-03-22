package br.edu.ifbasaj.xadrez.domain.strategy;

import br.edu.ifbasaj.xadrez.domain.model.Posicao;
import br.edu.ifbasaj.xadrez.domain.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class MovimentoHorizontalVertical implements MovimentoStrategy {

    @Override
    public List<Posicao> calcularMovimentosValidos(Tabuleiro tabuleiro, Posicao posicaoAtual) {
        List<Posicao> movimentos = new ArrayList<>();

        int linha = posicaoAtual.getLinha();
        int coluna = posicaoAtual.getColuna();

        // Cima
        for (int l = linha - 1; l >= 0; l--) {
            if (!tabuleiro.estaDentroDoLimite(l, coluna)) break;
            movimentos.add(new Posicao(l, coluna));
        }

        // Baixo
        for (int l = linha + 1; l < 8; l++) {
            if (!tabuleiro.estaDentroDoLimite(l, coluna)) break;
            movimentos.add(new Posicao(l, coluna));
        }

        // Esquerda
        for (int c = coluna - 1; c >= 0; c--) {
            if (!tabuleiro.estaDentroDoLimite(linha, c)) break;
            movimentos.add(new Posicao(linha, c));
        }

        // Direita
        for (int c = coluna + 1; c < 8; c++) {
            if (!tabuleiro.estaDentroDoLimite(linha, c)) break;
            movimentos.add(new Posicao(linha, c));
        }

        return movimentos;
    }
}


