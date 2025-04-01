package com.jogo.memoria.jogo_da_memoria.model;

import java.util.Collections;
import java.util.List;

public class GameBoard6x6 extends AbstractGameBoard {

    public GameBoard6x6(CardFactory cardFactory) {
        super(cardFactory, 6); // Tabuleiro 6x6
        attemptsLeft = 12;
    }

    @Override
    public boolean isMatch() {
        // Verifica se exatamente 3 cartas foram viradas e se todas possuem o mesmo valor
        return flippedIndices.size() == 3 && cards.get(flippedIndices.get(0)).getValue() == cards.get(flippedIndices.get(1)).getValue() && cards.get(flippedIndices.get(1)).getValue() == cards.get(flippedIndices.get(2)).getValue();
    }

    @Override
    protected int getMaxFlippedCards() {
        return 3; // Para o modo de trios, apenas três cartas podem ser viradas
    }
    @Override
	public void reset() {
		for (Card card : cards) {
			card.setFlipped(false);
			card.setMatched(false);
		}
		Collections.shuffle(cards);
		flippedIndices.clear();
		attemptsLeft = 12;
		notifyObservers();
	}
}
