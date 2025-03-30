package com.jogo.memoria.jogo_da_memoria.controller;

import com.jogo.memoria.jogo_da_memoria.model.AbstractGameBoard;
import javax.swing.JButton;

public class FlipCardCommand implements Command {
    private AbstractGameBoard gameBoard;
    private int cardIndex;
    private JButton button;

    // Construtor que recebe o AbstractGameBoard, o índice da carta e o botão correspondente
    public FlipCardCommand(AbstractGameBoard gameBoard, int cardIndex, JButton button) {
        this.gameBoard = gameBoard;
        this.cardIndex = cardIndex;
        this.button = button;
    }

    // Método para executar o comando de virar a carta
    @Override
    public void execute() {
        gameBoard.flipCard(cardIndex); // Vira a carta no tabuleiro
        updateButton(); // Atualiza a interface
    }

    // Método para atualizar o botão com base no estado da carta
    private void updateButton() {
        // Verifica se a carta foi virada
        if (gameBoard.getCards().get(cardIndex).isFlipped()) {
            button.setText(String.valueOf(gameBoard.getCards().get(cardIndex).getValue())); // Mostra o valor da carta
            button.setBackground(java.awt.Color.CYAN); // Cor ciano para carta virada
        } else {
            button.setText(""); // Se a carta não for virada, não mostra o valor
            button.setBackground(java.awt.Color.GRAY); // Cor cinza para carta não virada
        }
    }
}

