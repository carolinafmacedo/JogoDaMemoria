package com.jogo.memoria.jogo_da_memoria.controller;

import com.jogo.memoria.jogo_da_memoria.model.GameBoard;
import javax.swing.JButton;

public class FlipCardCommand implements Command {
    private GameBoard gameBoard;
    private int cardIndex;
    private JButton button;

    // Construtor que recebe o GameBoard, o índice da carta e o botão correspondente
    public FlipCardCommand(GameBoard gameBoard, int cardIndex, JButton button) {
        this.gameBoard = gameBoard;
        this.cardIndex = cardIndex;
        this.button = button;
    }

    // Método para executar o comando de virar a carta
    @Override
    public void execute() {
        // Chama o método flipCard para virar a carta no GameBoard
        gameBoard.flipCard(cardIndex);
        
        // Atualiza a interface gráfica
        updateButton();
    }

    // Método para atualizar o texto e a cor do botão com base no estado da carta
    private void updateButton() {
        // Verifica o estado da carta (se está virada ou não)
        if (gameBoard.getCards().get(cardIndex).isFlipped()) {
            // Se a carta estiver virada, exibe o número no botão
            button.setText(String.valueOf(gameBoard.getCards().get(cardIndex).getValue()));
        } else {
            // Se não estiver virada, limpa o texto do botão
            button.setText("");
        }
    }
}
