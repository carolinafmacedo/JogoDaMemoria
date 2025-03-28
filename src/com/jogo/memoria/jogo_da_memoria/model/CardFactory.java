package com.jogo.memoria.jogo_da_memoria.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {
    // Cria a lista de cartas para o jogo
    public List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        // Gera números únicos de 1 a 8 para os pares
        for (int i = 1; i <= 8; i++) {
            cards.add(new Card(i));
            cards.add(new Card(i)); // Cria dois pares para cada número
        }
        // Embaralha as cartas para aleatoriedade
        Collections.shuffle(cards);
        return cards;
    }
}



