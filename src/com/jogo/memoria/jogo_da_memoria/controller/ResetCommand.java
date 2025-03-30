package com.jogo.memoria.jogo_da_memoria.controller;

import com.jogo.memoria.jogo_da_memoria.model.AbstractGameBoard;
import com.jogo.memoria.jogo_da_memoria.view.MemoryGameGUI;

public class ResetCommand implements Command {
    private AbstractGameBoard gameBoard;
    private MemoryGameGUI gui;

    public ResetCommand(AbstractGameBoard gameBoard, MemoryGameGUI gui) {
        this.gameBoard = gameBoard;
        this.gui = gui;
    }

    @Override
    public void execute() {
        gameBoard.reset();
        gui.updateButtons();
        gui.updateAttemptsLabel();

        if (gameBoard.isGameWon()) {
            gui.showGameWonMessage();
        } else if (gameBoard.isGameLost()) {
            gui.showGameLostMessage();
        }
    }
}
