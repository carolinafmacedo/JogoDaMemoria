package com.jogo.memoria.jogo_da_memoria.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.jogo.memoria.jogo_da_memoria.observer.GameBoardObserver;

public abstract class AbstractGameBoard {
    protected List<Card> cards;
    protected List<Integer> flippedIndices = new ArrayList<>();
    protected int attemptsLeft = 0;
    private boolean isCheckingMatch = false;
    private List<GameBoardObserver> observers = new ArrayList<>();

    //Cria lista de cartas
    public AbstractGameBoard(CardFactory cardFactory, int size) {
        this.cards = cardFactory.createCards(size);
    }

    // Retorna lista de cartas
    public List<Card> getCards() {
        return cards;
    }

    // Adiciona Observers
    public void addObserver(GameBoardObserver observer) {
        observers.add(observer);
    }

    //Notifica observers 
    public void notifyObservers() {
        for (GameBoardObserver observer : observers) {
            observer.onCardFlipped();
        }

        if (isGameWon()) {
            for (GameBoardObserver observer : observers) {
                observer.onGameWon();
            }
        } else if (isGameLost()) {
            for (GameBoardObserver observer : observers) {
                observer.onGameLost();
            }
        }
    }
    
    // Vira a carta e chama para notificar observers sobre a carta virada
    public void flipCard(int index) {
        if (isCheckingMatch || flippedIndices.contains(index) || cards.get(index).isMatched()) {
            return;
        }

        flippedIndices.add(index);
        cards.get(index).setFlipped(true);
        notifyObservers();

        if (flippedIndices.size() == getMaxFlippedCards()) {
            isCheckingMatch = true;
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkMatch();
            }).start();
        }
    }

    protected void checkMatch() {
        boolean isMatch = isMatch();
        if (isMatch) {
            for (int index : flippedIndices) {
                cards.get(index).setMatched(true);
            }
        } else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int index : flippedIndices) {
                cards.get(index).setFlipped(false);
            }
            decrementAttempts();
        }

        flippedIndices.clear();
        isCheckingMatch = false;
        notifyObservers();
    }

    protected abstract int getMaxFlippedCards();
    protected abstract boolean isMatch();

    public boolean isGameWon() {
        return cards.stream().allMatch(Card::isMatched);
    }

    public boolean isGameLost() {
        return attemptsLeft <= 0;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public void decrementAttempts() {
        if (attemptsLeft > 0) {
            attemptsLeft--;
        }
    }

    public abstract void reset();
     
}







