package br.edu.ifbasaj.xadrez.domain.command;

public interface JogadaCommand {
    void executar();
    void desfazer();
}
