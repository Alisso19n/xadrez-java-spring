package br.edu.ifbasaj.xadrez.domain.model;

import br.edu.ifbasaj.xadrez.domain.strategy.MovimentoPeao;

public class Peao extends Peca {

    public Peao(Cor cor) {
        super(cor, TipoPeca.PEAO, new MovimentoPeao());
    }
}
