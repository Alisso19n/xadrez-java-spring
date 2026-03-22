package br.edu.ifbasaj.xadrez.domain.model;

import br.edu.ifbasaj.xadrez.domain.strategy.MovimentoBispo;

public class Bispo extends Peca {

    public Bispo(Cor cor) {
        super(cor, TipoPeca.BISPO, new MovimentoBispo());
    }
}
