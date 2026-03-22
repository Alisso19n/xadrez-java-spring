package br.edu.ifbasaj.xadrez.domain.factory;

import br.edu.ifbasaj.xadrez.domain.model.*;

public class InicializadorTabuleiro {

    public static Tabuleiro criarTabuleiroInicial() {
        Tabuleiro tabuleiro = new Tabuleiro();

        // ----- Peças Pretas -----
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.TORRE, Cor.PRETO), new Posicao(0, 0));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.CAVALO, Cor.PRETO), new Posicao(0, 1));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.BISPO, Cor.PRETO), new Posicao(0, 2));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.RAINHA, Cor.PRETO), new Posicao(0, 3));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.REI, Cor.PRETO), new Posicao(0, 4));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.BISPO, Cor.PRETO), new Posicao(0, 5));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.CAVALO, Cor.PRETO), new Posicao(0, 6));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.TORRE, Cor.PRETO), new Posicao(0, 7));

        // Peões pretos
        for (int col = 0; col < 8; col++) {
            tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.PEAO, Cor.PRETO), new Posicao(1, col));
        }

        // ----- Peças Brancas -----
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.TORRE, Cor.BRANCO), new Posicao(7, 0));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.CAVALO, Cor.BRANCO), new Posicao(7, 1));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.BISPO, Cor.BRANCO), new Posicao(7, 2));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.RAINHA, Cor.BRANCO), new Posicao(7, 3));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.REI, Cor.BRANCO), new Posicao(7, 4));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.BISPO, Cor.BRANCO), new Posicao(7, 5));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.CAVALO, Cor.BRANCO), new Posicao(7, 6));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.TORRE, Cor.BRANCO), new Posicao(7, 7));

        // Peões brancos
        for (int col = 0; col < 8; col++) {
            tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.PEAO, Cor.BRANCO), new Posicao(6, col));
        }

        return tabuleiro;
    }
}
