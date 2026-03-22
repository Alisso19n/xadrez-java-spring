package br.edu.ifbasaj.xadrez.application.dto;

/**
 * DTO enviado do front para o backend quando o usuário tenta fazer um movimento.
 *
 * promocaoOpcional:
 *  - pode ser null (ou vazio) na maioria das jogadas
 *  - quando o peão chega no fim, pode vir algo como: "RAINHA", "TORRE", "BISPO", "CAVALO"
 */
public class MovimentoRequestDTO {

    private int origemLinha;
    private int origemColuna;
    private int destinoLinha;
    private int destinoColuna;

    // Opcional: usado quando houver promoção de peão
    private String promocaoOpcional;

    public MovimentoRequestDTO() {
    }

    public MovimentoRequestDTO(int origemLinha, int origemColuna, int destinoLinha, int destinoColuna) {
        this.origemLinha = origemLinha;
        this.origemColuna = origemColuna;
        this.destinoLinha = destinoLinha;
        this.destinoColuna = destinoColuna;
    }

    public MovimentoRequestDTO(int origemLinha, int origemColuna, int destinoLinha, int destinoColuna, String promocaoOpcional) {
        this.origemLinha = origemLinha;
        this.origemColuna = origemColuna;
        this.destinoLinha = destinoLinha;
        this.destinoColuna = destinoColuna;
        this.promocaoOpcional = promocaoOpcional;
    }

    public int getOrigemLinha() {
        return origemLinha;
    }

    public void setOrigemLinha(int origemLinha) {
        this.origemLinha = origemLinha;
    }

    public int getOrigemColuna() {
        return origemColuna;
    }

    public void setOrigemColuna(int origemColuna) {
        this.origemColuna = origemColuna;
    }

    public int getDestinoLinha() {
        return destinoLinha;
    }

    public void setDestinoLinha(int destinoLinha) {
        this.destinoLinha = destinoLinha;
    }

    public int getDestinoColuna() {
        return destinoColuna;
    }

    public void setDestinoColuna(int destinoColuna) {
        this.destinoColuna = destinoColuna;
    }

    public String getPromocaoOpcional() {
        return promocaoOpcional;
    }

    public void setPromocaoOpcional(String promocaoOpcional) {
        this.promocaoOpcional = promocaoOpcional;
    }
}
