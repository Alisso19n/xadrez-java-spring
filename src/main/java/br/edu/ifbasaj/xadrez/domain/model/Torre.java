package br.edu.ifbasaj.xadrez.domain.model;

import br.edu.ifbasaj.xadrez.domain.strategy.MovimentoTorre;

public class Torre extends Peca {

    public Torre(Cor cor) {
        super(cor, TipoPeca.TORRE, new MovimentoTorre());
    }
}
