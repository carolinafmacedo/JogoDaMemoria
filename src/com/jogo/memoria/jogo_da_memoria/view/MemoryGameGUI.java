package com.jogo.memoria.jogo_da_memoria.view;

import com.jogo.memoria.jogo_da_memoria.controller.MemoryGameController;
import com.jogo.memoria.jogo_da_memoria.model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemoryGameGUI extends JFrame {
    private MemoryGameController controller;
    private JPanel boardPanel;
    private JLabel attemptsLabel;
    private JButton[] buttons;

    public MemoryGameGUI() {
        initializeUI();
    }

    public void setController(MemoryGameController controller) {
        this.controller = controller;
    }

    private void initializeUI() {
        setTitle("Jogo da Memória");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new JPanel(new GridLayout(4, 4));
        add(boardPanel, BorderLayout.CENTER);

        attemptsLabel = new JLabel("Tentativas restantes: 6");
        add(attemptsLabel, BorderLayout.NORTH);

        buttons = new JButton[16];  // Criando um array para armazenar os botões

        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            buttons[i].setText("");
            final int index = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (controller != null) {
                        controller.flipCard(index);
                    }
                }
            });
            boardPanel.add(buttons[i]);
        }
    }

    public void updateUI() {
        if (controller == null) return;

        attemptsLabel.setText("Tentativas restantes: " + controller.getGameBoard().getAttemptsLeft());

        for (int i = 0; i < controller.getGameBoard().getCards().size(); i++) {
            Card card = controller.getGameBoard().getCards().get(i);
            JButton button = buttons[i];

            if (card.isFlipped()) {
                button.setText(String.valueOf(card.getValue()));
                if (card.isMatched()) {
                    button.setBackground(Color.GREEN);  // Se for um par correto, fica verde
                    button.setEnabled(false);
                }
            } else {
                button.setText("");
                button.setBackground(null);
                button.setEnabled(true);
            }
        }

        if (controller.isGameWon()) {
            JOptionPane.showMessageDialog(this, "Você ganhou!");
            disableAllButtons();
        } else if (controller.isGameLost()) {
            JOptionPane.showMessageDialog(this, "Você perdeu!");
            disableAllButtons();
        }
    }

    private void disableAllButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
}



