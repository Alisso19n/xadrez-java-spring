package br.edu.ifbasaj.xadrez.domain.model;

import br.edu.ifbasaj.xadrez.domain.strategy.MovimentoRainha;

public class Rainha extends Peca {

    public Rainha(Cor cor) {
        super(cor, TipoPeca.RAINHA, new MovimentoRainha());
    }
}
