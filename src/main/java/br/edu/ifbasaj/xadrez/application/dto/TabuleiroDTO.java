package br.edu.ifbasaj.xadrez.application.dto;

/**
 * Representa o estado do tabuleiro para o front.
 */
public class TabuleiroDTO {

    private PecaDTO[][] casas;

    // novos campos para mensagens e xeque
    private String mensagem;
    private boolean xequeBrancas;
    private boolean xequePretas;

    public TabuleiroDTO() {
    }

    public TabuleiroDTO(PecaDTO[][] casas) {
        this(casas, null, false, false);
    }

    public TabuleiroDTO(PecaDTO[][] casas,
                        String mensagem,
                        boolean xequeBrancas,
                        boolean xequePretas) {
        this.casas = casas;
        this.mensagem = mensagem;
        this.xequeBrancas = xequeBrancas;
        this.xequePretas = xequePretas;
    }

    public PecaDTO[][] getCasas() {
        return casas;
    }

    public void setCasas(PecaDTO[][] casas) {
        this.casas = casas;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isXequeBrancas() {
        return xequeBrancas;
    }

    public void setXequeBrancas(boolean xequeBrancas) {
        this.xequeBrancas = xequeBrancas;
    }

    public boolean isXequePretas() {
        return xequePretas;
    }

    public void setXequePretas(boolean xequePretas) {
        this.xequePretas = xequePretas;
    }
}
