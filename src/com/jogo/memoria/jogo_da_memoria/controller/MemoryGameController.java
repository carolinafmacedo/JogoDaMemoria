package com.jogo.memoria.jogo_da_memoria.controller;

import com.jogo.memoria.jogo_da_memoria.model.GameBoard;
import com.jogo.memoria.jogo_da_memoria.model.GameBoardObserver;
import com.jogo.memoria.jogo_da_memoria.view.MemoryGameGUI;

import javax.swing.*;

public class MemoryGameController implements GameBoardObserver {
    private GameBoard gameBoard;
    private MemoryGameGUI gui;

    public MemoryGameController(GameBoard gameBoard, MemoryGameGUI gui) {
        this.gameBoard = gameBoard;
        this.gui = gui;
        this.gameBoard.addObserver(this);
    }

    public void flipCard(int index) {
        gameBoard.flipCard(index);
        SwingUtilities.invokeLater(() -> gui.updateUI(gameBoard.getCards(), gameBoard.getAttemptsLeft()));
    }

    @Override
    public void update(boolean matchFound) {
        SwingUtilities.invokeLater(() -> gui.updateUI(gameBoard.getCards(), gameBoard.getAttemptsLeft()));
    }

    public boolean isGameWon() {
        return gameBoard.isGameWon();
    }

    public boolean isGameLost() {
        return gameBoard.isGameLost();
    }
}



