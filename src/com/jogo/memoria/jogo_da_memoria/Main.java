package com.jogo.memoria.jogo_da_memoria;

import com.jogo.memoria.jogo_da_memoria.controller.MemoryGameController;
import com.jogo.memoria.jogo_da_memoria.view.MemoryGameGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] options = {"4x4 - Pares (Fácil)", "6x6 - Trios (Difícil)"};
            int choice = JOptionPane.showOptionDialog(null, "Escolha o modo de jogo:",
                    "Modo de Jogo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            // Fecha a aplicação caso o usuário clique em "Cancelar" ou feche a caixa de diálogo
            if (choice == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }

            boolean isTrioMode = (choice == 1);
            int gridSize = isTrioMode ? 6 : 4;

            // Cria a interface gráfica
            MemoryGameGUI gui = new MemoryGameGUI(gridSize);
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Agora fecha corretamente ao clicar no "X"

            // Cria o controlador e vincula à GUI
            MemoryGameController controller = new MemoryGameController(gridSize, gui);
            gui.setController(controller);
        });
    }
}




