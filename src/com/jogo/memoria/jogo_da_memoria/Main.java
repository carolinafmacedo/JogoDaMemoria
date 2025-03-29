package com.jogo.memoria.jogo_da_memoria;

import com.jogo.memoria.jogo_da_memoria.controller.MemoryGameController;
import com.jogo.memoria.jogo_da_memoria.model.CardFactory;
import com.jogo.memoria.jogo_da_memoria.model.GameBoard;
import com.jogo.memoria.jogo_da_memoria.view.MemoryGameGUI;

public class Main {
    public static void main(String[] args) {
        CardFactory cardFactory = new CardFactory();
        GameBoard gameBoard = new GameBoard(cardFactory);
        MemoryGameGUI gui = new MemoryGameGUI();
        MemoryGameController controller = new MemoryGameController(gameBoard, gui);
        
        gui.setController(controller);
        gui.initializeUI();
    }
}
