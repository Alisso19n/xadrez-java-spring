package br.edu.ifbasaj.xadrez.domain.model;

import br.edu.ifbasaj.xadrez.domain.strategy.MovimentoRei;

public class Rei extends Peca {

    public Rei(Cor cor) {
        super(cor, TipoPeca.REI, new MovimentoRei());
    }
}
