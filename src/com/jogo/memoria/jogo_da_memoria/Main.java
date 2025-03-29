package com.jogo.memoria.jogo_da_memoria;

import com.jogo.memoria.jogo_da_memoria.controller.MemoryGameController;
import com.jogo.memoria.jogo_da_memoria.model.CardFactory;
import com.jogo.memoria.jogo_da_memoria.model.GameBoard;
import com.jogo.memoria.jogo_da_memoria.model.SimpleCardFactory;
import com.jogo.memoria.jogo_da_memoria.view.MemoryGameGUI;

public class Main {
    public static void main(String[] args) {
        CardFactory cardFactory = new SimpleCardFactory();  // Usa o Factory Method
        GameBoard gameBoard = new GameBoard(cardFactory);
        
        // Criação da interface gráfica
        MemoryGameGUI gui = new MemoryGameGUI();

        // Passando tanto o gameBoard quanto a GUI para o Controller
        MemoryGameController controller = new MemoryGameController(gameBoard, gui);
        
        // Agora informamos o controller para a GUI
        gui.setController(controller);

        gui.setVisible(true);
    }
}
