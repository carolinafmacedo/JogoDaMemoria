package com.jogo.memoria.jogo_da_memoria.controller;

/**
 * Interface Command faz parte do padrão de projeto Command.
 * 
 * Esse padrão encapsula uma ação como um objeto, permitindo que comandos 
 * sejam armazenados, passados e executados dinamicamente. 
 * 
 * No contexto do Jogo da Memória, essa interface pode ser utilizada para 
 * implementar ações como virar uma carta, resetar o jogo ou até desfazer uma jogada.
 */
public interface Command {
    /**
     * Método que executa a ação definida pelo comando.
     */
    void execute();
}
