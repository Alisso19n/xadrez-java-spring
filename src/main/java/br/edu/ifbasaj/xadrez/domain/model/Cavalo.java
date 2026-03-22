package br.edu.ifbasaj.xadrez.domain.model;

import br.edu.ifbasaj.xadrez.domain.strategy.MovimentoCavalo;

public class Cavalo extends Peca {

    public Cavalo(Cor cor) {
        super(cor, TipoPeca.CAVALO, new MovimentoCavalo());
    }
}
