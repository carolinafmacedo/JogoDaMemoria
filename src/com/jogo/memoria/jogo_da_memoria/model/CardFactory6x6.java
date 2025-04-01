package com.jogo.memoria.jogo_da_memoria.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory6x6 implements CardFactory {
    @Override
    public List<Card> createCards(int size) {
        List<Card> cards = new ArrayList<>();
        int totalTrios = size * size / 3; 

        for (int i = 0; i < totalTrios; i++) {
            cards.add(new Card(i + 1));
            cards.add(new Card(i + 1));
            cards.add(new Card(i + 1));
        }

        Collections.shuffle(cards);
        return cards;
    }
}
