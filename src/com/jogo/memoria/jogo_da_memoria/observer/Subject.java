package com.jogo.memoria.jogo_da_memoria.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<GameBoardObserver> observers = new ArrayList<>();

    public void addObserver(GameBoardObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameBoardObserver observer) {
        observers.remove(observer);
    }

    public void notifyCardFlipped() {
        for (GameBoardObserver observer : observers) {
            observer.onCardFlipped();
        }
    }

    public void notifyGameWon() {
        for (GameBoardObserver observer : observers) {
            observer.onGameWon();
        }
    }

    public void notifyGameLost() {
        for (GameBoardObserver observer : observers) {
            observer.onGameLost();
        }
    }
}
