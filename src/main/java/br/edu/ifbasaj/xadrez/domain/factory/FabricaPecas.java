package br.edu.ifbasaj.xadrez.domain.factory;

import br.edu.ifbasaj.xadrez.domain.model.*;

public class FabricaPecas {

    public static Peca criar(TipoPeca tipo, Cor cor) {
        return switch (tipo) {
            case TORRE -> new Torre(cor);
            case BISPO -> new Bispo(cor);
            case CAVALO -> new Cavalo(cor);
            case RAINHA -> new Rainha(cor);
            case REI   -> new Rei(cor);
            case PEAO  -> new Peao(cor);
        };
    }
}
