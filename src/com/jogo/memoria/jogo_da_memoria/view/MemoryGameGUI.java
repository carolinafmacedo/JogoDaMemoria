package com.jogo.memoria.jogo_da_memoria.view;

import com.jogo.memoria.jogo_da_memoria.controller.MemoryGameController;
import com.jogo.memoria.jogo_da_memoria.model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MemoryGameGUI extends JFrame {
    private MemoryGameController controller;
    private JPanel boardPanel;
    private JLabel attemptsLabel;
    private JButton[] buttons;

    public MemoryGameGUI() {
        setTitle("Jogo da Memória");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new JPanel(new GridLayout(4, 4));
        add(boardPanel, BorderLayout.CENTER);

        attemptsLabel = new JLabel("Tentativas restantes: 6", SwingConstants.CENTER);
        add(attemptsLabel, BorderLayout.NORTH);
        
        setVisible(true);
    }

    public void setController(MemoryGameController controller) {
        this.controller = controller;
    }

    public void initializeUI() {
        buttons = new JButton[16];

        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            buttons[i].setBackground(Color.LIGHT_GRAY);
            final int index = i;
            buttons[i].addActionListener(e -> controller.flipCard(index));
            boardPanel.add(buttons[i]);
        }
        
        setVisible(true);
    }

    public void updateUI(List<Card> cards, int attemptsLeft) {
        attemptsLabel.setText("Tentativas restantes: " + attemptsLeft);

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.isFlipped()) {
                buttons[i].setText(String.valueOf(card.getValue()));
                buttons[i].setBackground(card.isMatched() ? Color.GREEN : Color.WHITE);
            } else {
                buttons[i].setText("");
                buttons[i].setBackground(Color.LIGHT_GRAY);
            }
        }

        if (controller.isGameWon()) {
            JOptionPane.showMessageDialog(this, "Parabéns! Você ganhou!");
        } else if (controller.isGameLost()) {
            JOptionPane.showMessageDialog(this, "Game Over! Você perdeu!");
        }
    }
}





