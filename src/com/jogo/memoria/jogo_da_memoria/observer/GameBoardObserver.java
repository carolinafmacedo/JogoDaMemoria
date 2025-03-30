package com.jogo.memoria.jogo_da_memoria.observer;

public interface GameBoardObserver {
    void onCardFlipped();
    void onGameWon();
    void onGameLost();
}
