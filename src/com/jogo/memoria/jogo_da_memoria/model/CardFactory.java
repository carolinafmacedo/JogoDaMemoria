package com.jogo.memoria.jogo_da_memoria.model;

import java.util.List;

public interface CardFactory {
    List<Card> createCards(int size);
}
