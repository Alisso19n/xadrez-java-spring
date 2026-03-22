package br.edu.ifbasaj.xadrez.domain.strategy;

import br.edu.ifbasaj.xadrez.domain.model.Cor;
import br.edu.ifbasaj.xadrez.domain.model.Peca;
import br.edu.ifbasaj.xadrez.domain.model.Posicao;
import br.edu.ifbasaj.xadrez.domain.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovimentoCavalo implements MovimentoStrategy {

    private static final int[][] MOVIMENTOS = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2},  {1, 2},
            {2, -1},  {2, 1}
    };

    @Override
    public List<Posicao> calcularMovimentosValidos(Tabuleiro tabuleiro, Posicao posicaoAtual) {
        List<Posicao> movimentos = new ArrayList<>();

        Optional<Peca> pecaOpt = tabuleiro.getPeca(posicaoAtual);
        if (pecaOpt.isEmpty()) return movimentos;

        Cor cor = pecaOpt.get().getCor();

        for (int[] mov : MOVIMENTOS) {
            int linha = posicaoAtual.getLinha() + mov[0];
            int coluna = posicaoAtual.getColuna() + mov[1];

            if (!tabuleiro.estaDentroDoLimite(linha, coluna)) continue;

            Posicao novaPos = new Posicao(linha, coluna);
            Optional<Peca> pecaDestino = tabuleiro.getPeca(novaPos);

            if (pecaDestino.isEmpty() || pecaDestino.get().getCor() != cor) {
                movimentos.add(novaPos);
            }
        }

        return movimentos;
    }
}
