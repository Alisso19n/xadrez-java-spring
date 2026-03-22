package br.edu.ifbasaj.xadrez.domain.strategy;

import br.edu.ifbasaj.xadrez.domain.model.Cor;
import br.edu.ifbasaj.xadrez.domain.model.Peca;
import br.edu.ifbasaj.xadrez.domain.model.Posicao;
import br.edu.ifbasaj.xadrez.domain.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovimentoRei implements MovimentoStrategy {

    @Override
    public List<Posicao> calcularMovimentosValidos(Tabuleiro tabuleiro, Posicao posicaoAtual) {
        List<Posicao> movimentos = new ArrayList<>();

        Optional<Peca> pecaOpt = tabuleiro.getPeca(posicaoAtual);
        if (pecaOpt.isEmpty()) return movimentos;

        Cor cor = pecaOpt.get().getCor();

        for (int dLinha = -1; dLinha <= 1; dLinha++) {
            for (int dCol = -1; dCol <= 1; dCol++) {
                if (dLinha == 0 && dCol == 0) continue;

                int linha = posicaoAtual.getLinha() + dLinha;
                int coluna = posicaoAtual.getColuna() + dCol;

                if (!tabuleiro.estaDentroDoLimite(linha, coluna)) continue;

                Posicao novaPos = new Posicao(linha, coluna);
                Optional<Peca> pecaDestino = tabuleiro.getPeca(novaPos);

                if (pecaDestino.isEmpty() || pecaDestino.get().getCor() != cor) {
                    movimentos.add(novaPos);
                }
            }
        }

        // (Sem roque por enquanto)
        return movimentos;
    }
}
