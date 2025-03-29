package com.jogo.memoria.jogo_da_memoria.model;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private List<Card> cards;
    private List<GameBoardObserver> observers;
    private Card firstFlippedCard;
    private Card secondFlippedCard;
    private int flippedCount;
    private int attemptsLeft;

    public GameBoard(CardFactory cardFactory) {
        this.cards = cardFactory.createCards();
        this.observers = new ArrayList<>();
        this.firstFlippedCard = null;
        this.secondFlippedCard = null;
        this.flippedCount = 0;
        this.attemptsLeft = 6;
    }

    public void addObserver(GameBoardObserver observer) {
        observers.add(observer);
    }

    public void flipCard(int index) {
        Card card = cards.get(index);
        if (card.isFlipped() || attemptsLeft <= 0 || firstFlippedCard != null && secondFlippedCard != null) {
            return;
        }

        card.flip();

        if (firstFlippedCard == null) {
            firstFlippedCard = card;
        } else {
            secondFlippedCard = card;
            checkMatch();
        }

        notifyObservers(false);
    }

    private void checkMatch() {
        if (firstFlippedCard.getValue() == secondFlippedCard.getValue()) {
            firstFlippedCard.setMatched(true);
            secondFlippedCard.setMatched(true);
            flippedCount += 2;

            firstFlippedCard = null;
            secondFlippedCard = null;

            notifyObservers(true);
        } else {
            attemptsLeft--;
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                firstFlippedCard.flip();
                secondFlippedCard.flip();
                firstFlippedCard = null;
                secondFlippedCard = null;
                notifyObservers(false);
            }).start();
        }
    }

    private void notifyObservers(boolean matchFound) {
        for (GameBoardObserver observer : observers) {
            observer.update(matchFound);
        }
    }

    public boolean isGameWon() {
        return flippedCount == cards.size();
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public List<Card> getCards() {
        return cards;
    }
}



