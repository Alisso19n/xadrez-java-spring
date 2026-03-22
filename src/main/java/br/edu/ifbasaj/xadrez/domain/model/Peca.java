package br.edu.ifbasaj.xadrez.domain.model;

import br.edu.ifbasaj.xadrez.domain.strategy.MovimentoStrategy;

import java.util.List;

public abstract class Peca {

    private final Cor cor;
    private final TipoPeca tipo;
    protected MovimentoStrategy movimentoStrategy;

    protected Peca(Cor cor, TipoPeca tipo, MovimentoStrategy movimentoStrategy) {
        this.cor = cor;
        this.tipo = tipo;
        this.movimentoStrategy = movimentoStrategy;
    }

    public Cor getCor() {
        return cor;
    }

    public TipoPeca getTipo() {
        return tipo;
    }

    public List<Posicao> movimentosPossiveis(Tabuleiro tabuleiro, Posicao posicaoAtual) {
        return movimentoStrategy.calcularMovimentosValidos(tabuleiro, posicaoAtual);
    }

    private boolean jaMoveu = false;

    public boolean isJaMoveu() {
        return jaMoveu;
    }

    public void setJaMoveu(boolean jaMoveu) {
        this.jaMoveu = jaMoveu;
    }

}
