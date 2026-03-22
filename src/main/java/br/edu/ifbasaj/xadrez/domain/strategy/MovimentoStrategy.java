package br.edu.ifbasaj.xadrez.domain.strategy;

import br.edu.ifbasaj.xadrez.domain.model.Posicao;
import br.edu.ifbasaj.xadrez.domain.model.Tabuleiro;

import java.util.Collections;
import java.util.List;

/**
 * Strategy para cálculo de movimentos válidos.
 */
public interface MovimentoStrategy {

    List<Posicao> calcularMovimentosValidos(Tabuleiro tabuleiro, Posicao posicaoAtual);

    /**
     * Implementação vazia útil para testes iniciais.
     */
    MovimentoStrategy SEM_MOVIMENTO = (tabuleiro, posicaoAtual) -> Collections.emptyList();
}
