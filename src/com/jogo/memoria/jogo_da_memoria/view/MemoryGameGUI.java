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

    public MemoryGameGUI(MemoryGameController controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Jogo da Memória");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new JPanel(new GridLayout(4, 4)); // Tabuleiro 4x4
        add(boardPanel, BorderLayout.CENTER);

        attemptsLabel = new JLabel("Tentativas restantes: 6");
        add(attemptsLabel, BorderLayout.NORTH); // Exibindo as tentativas acima do board

        // Adiciona os botões ao tabuleiro
        for (int i = 0; i < 16; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(80, 80));
            button.setText(""); // Inicialmente, as cartas não têm texto
            final int index = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.flipCard(index, button); // Virar carta e atualizar a interface
                    updateUI(); // Atualiza a interface após a ação
                }
            });
            boardPanel.add(button);
            controller.getGameBoard().addObserverButton(button); // Adiciona o botão à lista de observadores
        }

        setVisible(true);
    }

    private void updateUI() {
        // Atualiza o label de tentativas
        attemptsLabel.setText("Tentativas restantes: " + controller.getGameBoard().getAttemptsLeft());

        // Atualiza os textos dos botões e as cores
        for (int i = 0; i < controller.getGameBoard().getCards().size(); i++) {
            Card card = controller.getGameBoard().getCards().get(i);
            JButton button = (JButton) controller.getGameBoard().getObserverButtons().get(i);

            if (card.isFlipped()) {
                button.setText(String.valueOf(card.getValue())); // Exibe o valor
                button.setEnabled(false); // Desabilita o botão da carta virada
            } else {
                button.setText(""); // Limpa o texto da carta não virada
                button.setEnabled(true); // Habilita o botão da carta não virada
            }
        }
    }
}



