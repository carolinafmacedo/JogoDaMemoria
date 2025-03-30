package com.jogo.memoria.jogo_da_memoria.model;

import java.util.List;

public class GameBoard4x4 extends AbstractGameBoard {

    public GameBoard4x4(CardFactory cardFactory) {
        super(cardFactory, 4); // Tabuleiro 4x4
    }

    @Override
    public boolean isMatch() {
        return flippedIndices.size() == 2 && cards.get(flippedIndices.get(0)).getValue() == cards.get(flippedIndices.get(1)).getValue();
    }

    @Override
    protected int getMaxFlippedCards() {
        return 2; // Para o modo de pares, apenas duas cartas podem ser viradas
    }
}
