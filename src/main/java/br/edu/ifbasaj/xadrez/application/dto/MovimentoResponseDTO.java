package br.edu.ifbasaj.xadrez.application.dto;

public class MovimentoResponseDTO {

    private boolean sucesso;
    private String mensagem;
    private String vez;

    private boolean xequeBrancas;
    private boolean xequePretas;

    private boolean mateBrancas;
    private boolean matePretas;

    private TabuleiroDTO tabuleiro;

    public MovimentoResponseDTO() {}

    public MovimentoResponseDTO(boolean sucesso, String mensagem, String vez,
                                boolean xequeBrancas, boolean xequePretas,
                                boolean mateBrancas, boolean matePretas,
                                TabuleiroDTO tabuleiro) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.vez = vez;
        this.xequeBrancas = xequeBrancas;
        this.xequePretas = xequePretas;
        this.mateBrancas = mateBrancas;
        this.matePretas = matePretas;
        this.tabuleiro = tabuleiro;
    }

    public boolean isSucesso() { return sucesso; }
    public void setSucesso(boolean sucesso) { this.sucesso = sucesso; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getVez() { return vez; }
    public void setVez(String vez) { this.vez = vez; }

    public boolean isXequeBrancas() { return xequeBrancas; }
    public void setXequeBrancas(boolean xequeBrancas) { this.xequeBrancas = xequeBrancas; }

    public boolean isXequePretas() { return xequePretas; }
    public void setXequePretas(boolean xequePretas) { this.xequePretas = xequePretas; }

    public boolean isMateBrancas() { return mateBrancas; }
    public void setMateBrancas(boolean mateBrancas) { this.mateBrancas = mateBrancas; }

    public boolean isMatePretas() { return matePretas; }
    public void setMatePretas(boolean matePretas) { this.matePretas = matePretas; }

    public TabuleiroDTO getTabuleiro() { return tabuleiro; }
    public void setTabuleiro(TabuleiroDTO tabuleiro) { this.tabuleiro = tabuleiro; }
}
