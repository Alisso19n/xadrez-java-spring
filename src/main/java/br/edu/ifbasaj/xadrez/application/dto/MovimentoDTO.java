package br.edu.ifbasaj.xadrez.application.dto;

public class MovimentoDTO {

    private int linhaOrigem;
    private int colunaOrigem;
    private int linhaDestino;
    private int colunaDestino;

    public MovimentoDTO() {
    }

    public int getLinhaOrigem() {
        return linhaOrigem;
    }

    public void setLinhaOrigem(int linhaOrigem) {
        this.linhaOrigem = linhaOrigem;
    }

    public int getColunaOrigem() {
        return colunaOrigem;
    }

    public void setColunaOrigem(int colunaOrigem) {
        this.colunaOrigem = colunaOrigem;
    }

    public int getLinhaDestino() {
        return linhaDestino;
    }

    public void setLinhaDestino(int linhaDestino) {
        this.linhaDestino = linhaDestino;
    }

    public int getColunaDestino() {
        return colunaDestino;
    }

    public void setColunaDestino(int colunaDestino) {
        this.colunaDestino = colunaDestino;
    }
}
