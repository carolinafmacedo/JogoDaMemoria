package com.jogo.memoria.jogo_da_memoria.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleCardFactory extends CardFactory {

    @Override
    public List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        
        // Aqui podemos criar um conjunto de cartas, com valores duplicados para formar os pares
        for (int i = 1; i <= 8; i++) {
            cards.add(new Card(i));
            cards.add(new Card(i));  // Adiciona o par
        }
        
        Collections.shuffle(cards);  // Embaralha as cartas
        return cards;
    }
}
