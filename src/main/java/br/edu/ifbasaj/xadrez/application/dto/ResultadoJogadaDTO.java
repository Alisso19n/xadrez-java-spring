package br.edu.ifbasaj.xadrez.application.dto;

public class ResultadoJogadaDTO {

    private boolean sucesso;
    private String mensagem;
    private TabuleiroDTO tabuleiro;

    public ResultadoJogadaDTO() {
    }

    public ResultadoJogadaDTO(boolean sucesso, String mensagem, TabuleiroDTO tabuleiro) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.tabuleiro = tabuleiro;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public TabuleiroDTO getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(TabuleiroDTO tabuleiro) {
        this.tabuleiro = tabuleiro;
    }
}
