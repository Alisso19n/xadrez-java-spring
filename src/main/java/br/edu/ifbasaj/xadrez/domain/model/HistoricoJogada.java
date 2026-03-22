package br.edu.ifbasaj.xadrez.domain.model;

public class HistoricoJogada {

    public Posicao origem;
    public Posicao destino;

    public Peca pecaMovida;
    public Peca pecaCapturada;

    public boolean pecaMovidaJaMoveuAntes;

    // En passant
    public boolean foiEnPassant;
    public Posicao posicaoPeaoCapturadoEnPassant;
    public Peca peaoCapturadoEnPassant;

    // Roque
    public boolean foiRoque;
    public Posicao origemTorre;
    public Posicao destinoTorre;
    public Peca torre;
    public boolean torreJaMoveuAntes;

    // Promoção
    public boolean foiPromocao;
    public Peca peaoOriginal;
    public Peca pecaPromovida;

    // Estado
    public Cor vezAnterior;
    public Movimento ultimoMovimentoAnterior;
}
