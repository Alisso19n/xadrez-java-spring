package br.edu.ifbasaj.xadrez.domain.factory;

import br.edu.ifbasaj.xadrez.domain.model.*;

public class TabuleiroFactory {

    private TabuleiroFactory() {
        // Utility class
    }

    public static Tabuleiro criarTabuleiroInicial() {
        Tabuleiro tabuleiro = new Tabuleiro();

        // Peças pretas (linha 0 e 1)
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.TORRE, Cor.PRETO),  new Posicao(0, 0));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.CAVALO, Cor.PRETO), new Posicao(0, 1));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.BISPO, Cor.PRETO),  new Posicao(0, 2));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.RAINHA, Cor.PRETO), new Posicao(0, 3));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.REI, Cor.PRETO),    new Posicao(0, 4));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.BISPO, Cor.PRETO),  new Posicao(0, 5));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.CAVALO, Cor.PRETO), new Posicao(0, 6));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.TORRE, Cor.PRETO),  new Posicao(0, 7));

        for (int coluna = 0; coluna < 8; coluna++) {
            tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.PEAO, Cor.PRETO), new Posicao(1, coluna));
        }

        // Peças brancas (linha 7 e 6)
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.TORRE, Cor.BRANCO),  new Posicao(7, 0));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.CAVALO, Cor.BRANCO), new Posicao(7, 1));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.BISPO, Cor.BRANCO),  new Posicao(7, 2));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.RAINHA, Cor.BRANCO), new Posicao(7, 3));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.REI, Cor.BRANCO),    new Posicao(7, 4));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.BISPO, Cor.BRANCO),  new Posicao(7, 5));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.CAVALO, Cor.BRANCO), new Posicao(7, 6));
        tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.TORRE, Cor.BRANCO),  new Posicao(7, 7));

        for (int coluna = 0; coluna < 8; coluna++) {
            tabuleiro.colocarPeca(FabricaPecas.criar(TipoPeca.PEAO, Cor.BRANCO), new Posicao(6, coluna));
        }

        return tabuleiro;
    }
}
