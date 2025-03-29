package com.jogo.memoria.jogo_da_memoria.model;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private List<Card> cards;
    private List<GameBoardObserver> observers;
    private Card firstFlippedCard;
    private Card secondFlippedCard;
    private int attemptsLeft;
    private boolean gameWon;
    private boolean gameLost;

    public GameBoard(CardFactory cardFactory) {
        this.cards = cardFactory.createCards();
        this.observers = new ArrayList<>();
        this.attemptsLeft = 6;
        this.gameWon = false;
        this.gameLost = false;
    }

    public void addObserver(GameBoardObserver observer) {
        observers.add(observer);
    }

    public void flipCard(int index) {
        if (attemptsLeft <= 0 || gameWon || gameLost) return;

        Card card = cards.get(index);
        if (card.isFlipped() || (firstFlippedCard != null && secondFlippedCard != null)) return;

        card.flip();

        if (firstFlippedCard == null) {
            firstFlippedCard = card;
        } else {
            secondFlippedCard = card;
            checkMatch();
        }

        notifyObservers();
    }

    private void checkMatch() {
        if (firstFlippedCard.getValue() == secondFlippedCard.getValue()) {
            firstFlippedCard.setMatched(true);
            secondFlippedCard.setMatched(true);
            resetCards(false); // Não precisa virar de volta
        } else {
            attemptsLeft--;
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    resetCards(true); // Vira as cartas de volta
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        if (cards.stream().allMatch(Card::isMatched)) {
            gameWon = true;
        } else if (attemptsLeft == 0) {
            gameLost = true;
        }
    }

    private void resetCards(boolean flipBack) {
        if (flipBack) {
            firstFlippedCard.flip();
            secondFlippedCard.flip();
        }
        firstFlippedCard = null;
        secondFlippedCard = null;
        notifyObservers();
    }

    private void notifyObservers() {
        for (GameBoardObserver observer : observers) {
            observer.update(false);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameLost() {
        return gameLost;
    }
}

