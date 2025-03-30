package com.jogo.memoria.jogo_da_memoria.view;

import com.jogo.memoria.jogo_da_memoria.controller.MemoryGameController;
import com.jogo.memoria.jogo_da_memoria.controller.FlipCardCommand;
import com.jogo.memoria.jogo_da_memoria.model.AbstractGameBoard;
import com.jogo.memoria.jogo_da_memoria.model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MemoryGameGUI extends JFrame {
    private MemoryGameController controller;
    private final JPanel boardPanel;
    private final JLabel attemptsLabel;
    private final JButton[] buttons;

    public MemoryGameGUI(int gridSize) {
        this.setTitle("Jogo da Memória");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(gridSize, gridSize));
        buttons = new JButton[gridSize * gridSize];

        attemptsLabel = new JLabel("Tentativas restantes: 10");
        this.add(attemptsLabel, BorderLayout.NORTH);

        for (int i = 0; i < gridSize * gridSize; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.GRAY); // Inicialmente as cartas são cinzas
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            int finalI = i;
            buttons[i].addActionListener(e -> flipCard(finalI));
            boardPanel.add(buttons[i]);
        }
        this.add(boardPanel, BorderLayout.CENTER);

        controller = new MemoryGameController(gridSize, this);
        this.setVisible(true);
    }

    private void flipCard(int cardIndex) {
        AbstractGameBoard gameBoard = controller.getGameBoard();
        FlipCardCommand flipCardCommand = new FlipCardCommand(gameBoard, cardIndex, buttons[cardIndex]);
        flipCardCommand.execute();

        updateAttemptsLabel();
        updateButtons();

        // Verifica se o jogo terminou
        if (gameBoard.isGameWon()) {
            JOptionPane.showMessageDialog(this, "Você ganhou!");
        } else if (gameBoard.isGameLost()) {
            JOptionPane.showMessageDialog(this, "Você perdeu!");
        }
    }

    public void updateAttemptsLabel() {
        int attempts = controller.getGameBoard().getAttemptsLeft();
        attemptsLabel.setText("Tentativas restantes: " + attempts);
    }

    public void updateButtons() {
        AbstractGameBoard gameBoard = controller.getGameBoard();
        List<Card> cards = gameBoard.getCards();

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            
            if (card.isFlipped()) {
                buttons[i].setText(String.valueOf(card.getValue())); // Exibe o número da carta
                buttons[i].setBackground(Color.CYAN); // Cartas viradas ficam ciano

                if (card.isMatched()) {
                    buttons[i].setBackground(Color.GREEN); // Cartas combinadas ficam verdes
                }
            } else {
                buttons[i].setText(""); // Reseta o texto se a carta não estiver virada
                buttons[i].setBackground(Color.GRAY); // Cartas não viradas ficam cinzas
            }
        }
    }

    public void setController(MemoryGameController controller) {
        this.controller = controller;
    }

    public void showGameWonMessage() {
        JOptionPane.showMessageDialog(this, "Você ganhou!");
    }

    public void showGameLostMessage() {
        JOptionPane.showMessageDialog(this, "Você perdeu!");
    }
}


