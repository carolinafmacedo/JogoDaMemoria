package com.jogo.memoria.jogo_da_memoria.controller;

import com.jogo.memoria.jogo_da_memoria.model.GameBoard;
import com.jogo.memoria.jogo_da_memoria.model.GameBoardObserver;

import javax.swing.JButton;
import java.awt.Color;

public class MemoryGameController implements GameBoardObserver {
    private GameBoard gameBoard;

    public MemoryGameController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.gameBoard.addObserver(this); // Adiciona o controlador como observer
    }

    // Método para virar a carta
    public void flipCard(int index, JButton button) {
        // Evita virar cartas que já foram viradas
        if (gameBoard.getCards().get(index).isFlipped()) {
            return; // Ignora o clique se a carta já foi virada
        }

        gameBoard.flipCard(index); // Chama o método flipCard no GameBoard
        updateUI(); // Atualiza a interface após a ação
    }

    public boolean isGameWon() {
        return gameBoard.isGameWon();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    public void update(boolean matchFound) {
        // Atualiza a interface do jogo com base em se as cartas formaram um par
        System.out.println(matchFound ? "Par encontrado!" : "Nenhum par encontrado.");
    }

    // Atualiza a interface, exibindo o valor das cartas viradas
    public void updateUI() {
        // Atualiza a interface após cada jogada
        for (int i = 0; i < gameBoard.getCards().size(); i++) {
            JButton button = (JButton) gameBoard.getObserverButtons().get(i); // Lista de botões
            if (gameBoard.getCards().get(i).isFlipped()) {
                button.setText(String.valueOf(gameBoard.getCards().get(i).getValue())); // Exibe o valor
                button.setEnabled(false); // Desabilita o botão da carta virada
            } else {
                button.setText(""); // Limpa o texto da carta não virada
                button.setEnabled(true); // Habilita o botão da carta não virada
            }
        }

        // Verifica se o jogo acabou
        if (gameBoard.isGameWon()) {
            System.out.println("Você ganhou!");
        }
    }
}




