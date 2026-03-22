package br.edu.ifbasaj.xadrez.domain.strategy;

import br.edu.ifbasaj.xadrez.domain.model.Cor;
import br.edu.ifbasaj.xadrez.domain.model.Peca;
import br.edu.ifbasaj.xadrez.domain.model.Posicao;
import br.edu.ifbasaj.xadrez.domain.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovimentoRainha implements MovimentoStrategy {

    @Override
    public List<Posicao> calcularMovimentosValidos(Tabuleiro tabuleiro, Posicao posicaoAtual) {
        List<Posicao> movimentos = new ArrayList<>();

        Optional<Peca> pecaOpt = tabuleiro.getPeca(posicaoAtual);
        if (pecaOpt.isEmpty()) return movimentos;

        Cor cor = pecaOpt.get().getCor();

        int[][] direcoes = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},   // torre
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // bispo
        };

        for (int[] dir : direcoes) {
            int linha = posicaoAtual.getLinha();
            int coluna = posicaoAtual.getColuna();

            while (true) {
                linha += dir[0];
                coluna += dir[1];

                if (!tabuleiro.estaDentroDoLimite(linha, coluna)) {
                    break;
                }

                Posicao novaPos = new Posicao(linha, coluna);
                Optional<Peca> pecaDestino = tabuleiro.getPeca(novaPos);

                if (pecaDestino.isEmpty()) {
                    movimentos.add(novaPos);
                } else {
                    if (pecaDestino.get().getCor() != cor) {
                        movimentos.add(novaPos);
                    }
                    break;
                }
            }
        }

        return movimentos;
    }
}
