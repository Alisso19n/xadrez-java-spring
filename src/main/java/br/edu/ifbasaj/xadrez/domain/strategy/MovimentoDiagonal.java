package br.edu.ifbasaj.xadrez.domain.strategy;

import br.edu.ifbasaj.xadrez.domain.model.Posicao;
import br.edu.ifbasaj.xadrez.domain.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class MovimentoDiagonal implements MovimentoStrategy {

    @Override
    public List<Posicao> calcularMovimentosValidos(Tabuleiro tabuleiro, Posicao posicaoAtual) {
        List<Posicao> movimentos = new ArrayList<>();

        int linha = posicaoAtual.getLinha();
        int coluna = posicaoAtual.getColuna();

        // Diagonal superior-esquerda
        for (int l = linha - 1, c = coluna - 1; l >= 0 && c >= 0; l--, c--) {
            if (!tabuleiro.estaDentroDoLimite(l, c)) break;
            movimentos.add(new Posicao(l, c));
        }

        // Diagonal superior-direita
        for (int l = linha - 1, c = coluna + 1; l >= 0 && c < 8; l--, c++) {
            if (!tabuleiro.estaDentroDoLimite(l, c)) break;
            movimentos.add(new Posicao(l, c));
        }

        // Diagonal inferior-esquerda
        for (int l = linha + 1, c = coluna - 1; l < 8 && c >= 0; l++, c--) {
            if (!tabuleiro.estaDentroDoLimite(l, c)) break;
            movimentos.add(new Posicao(l, c));
        }

        // Diagonal inferior-direita
        for (int l = linha + 1, c = coluna + 1; l < 8 && c < 8; l++, c++) {
            if (!tabuleiro.estaDentroDoLimite(l, c)) break;
            movimentos.add(new Posicao(l, c));
        }

        return movimentos;
    }
}

