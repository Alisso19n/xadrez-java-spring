package br.edu.ifbasaj.xadrez.application.dto;

public class CasaDTO {

    private int linha;
    private int coluna;
    private String tipoPeca; // pode ser null
    private String corPeca;  // pode ser null

    public CasaDTO(int linha, int coluna, String tipoPeca, String corPeca) {
        this.linha = linha;
        this.coluna = coluna;
        this.tipoPeca = tipoPeca;
        this.corPeca = corPeca;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public String getTipoPeca() {
        return tipoPeca;
    }

    public String getCorPeca() {
        return corPeca;
    }
}
