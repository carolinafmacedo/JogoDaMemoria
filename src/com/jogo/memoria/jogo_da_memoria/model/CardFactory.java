package com.jogo.memoria.jogo_da_memoria.model;

import java.util.List;

/**
 * O Factory Method é utilizado aqui para padronizar e centralizar 
 * a criação das cartas do jogo da memória.
 *
 * Em vez de instanciar as cartas diretamente, esta classe abstrata 
 * define um método que suas subclasses concretas deverão implementar. 
 * Assim, podemos ter diferentes formas de criar e distribuir as cartas no tabuleiro.
 * 
 *Se quisermos mudar a lógica de criação 
 *(exemplo: diferentes modos de jogo), basta alterar uma subclasse.
 */
public abstract class CardFactory {
    
    /**
     * Método abstrato que será implementado pelas subclasses.
     * Cada implementação pode criar as cartas de uma maneira diferente.
     *
     * @return Uma lista de cartas para o jogo.
     */
    public abstract List<Card> createCards();
}

