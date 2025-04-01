package com.jogo.memoria.jogo_da_memoria.model;

import java.util.Collections;
import java.util.List;

public class GameBoard4x4 extends AbstractGameBoard {

	public GameBoard4x4(CardFactory cardFactory) {
		super(cardFactory, 4); // Tabuleiro 4x4
		attemptsLeft = 8;
	}

	@Override
	public boolean isMatch() {
		return flippedIndices.size() == 2
				&& cards.get(flippedIndices.get(0)).getValue() == cards.get(flippedIndices.get(1)).getValue();
	}

	@Override
	protected int getMaxFlippedCards() {
		return 2; // Para o modo de pares, apenas duas cartas podem ser viradas
	}

	@Override
	public void reset() {
		for (Card card : cards) {
			card.setFlipped(false);
			card.setMatched(false);
		}
		Collections.shuffle(cards);
		flippedIndices.clear();
		attemptsLeft = 8;
		notifyObservers();
	}
}
