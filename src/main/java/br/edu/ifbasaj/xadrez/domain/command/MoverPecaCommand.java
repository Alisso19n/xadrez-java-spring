package br.edu.ifbasaj.xadrez.domain.command;

import br.edu.ifbasaj.xadrez.domain.model.*;

public class MoverPecaCommand implements JogadaCommand {

    private final Tabuleiro tabuleiro;
    private final Posicao origem;
    private final Posicao destino;

    private Peca pecaMovida;
    private Peca pecaCapturada;

    public MoverPecaCommand(Tabuleiro tabuleiro, Posicao origem, Posicao destino) {
        this.tabuleiro = tabuleiro;
        this.origem = origem;
        this.destino = destino;
    }

    @Override
    public void executar() {
        pecaMovida = tabuleiro.getPeca(origem).orElseThrow();
        pecaCapturada = tabuleiro.mover(origem, destino);
    }

    @Override
    public void desfazer() {
        // volta peça
        tabuleiro.mover(destino, origem);
        // restaura capturada
        if (pecaCapturada != null) {
            tabuleiro.colocarPeca(pecaCapturada, destino);
        }
    }
}
