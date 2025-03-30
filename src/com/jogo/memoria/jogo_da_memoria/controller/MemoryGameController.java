package com.jogo.memoria.jogo_da_memoria.controller;

import com.jogo.memoria.jogo_da_memoria.model.AbstractGameBoard;
import com.jogo.memoria.jogo_da_memoria.view.MemoryGameGUI;
import com.jogo.memoria.jogo_da_memoria.observer.*;
import com.jogo.memoria.jogo_da_memoria.model.*;

public class MemoryGameController implements GameBoardObserver {
    private AbstractGameBoard gameBoard;
    private MemoryGameGUI gui;

    public MemoryGameController(int gridSize, MemoryGameGUI gui) {
        if (gridSize == 4) {
            this.gameBoard = new GameBoard4x4(new CardFactory4x4());
        } else if (gridSize == 6) {
            this.gameBoard = new GameBoard6x6(new CardFactory6x6());
        }
        this.gui = gui;
        this.gameBoard.addObserver(this); // Add the controller as an observer
    }

    public AbstractGameBoard getGameBoard() {
        return gameBoard;
    }

    public int getRemainingAttempts() {
        return gameBoard.getAttemptsLeft();
    }

    @Override
    public void onCardFlipped() {
        gui.updateButtons();
        gui.updateAttemptsLabel();
    }

    @Override
    public void onGameWon() {
        gui.showGameWonMessage();
    }

    @Override
    public void onGameLost() {
        gui.showGameLostMessage();
    }
}


