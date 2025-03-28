package com.jogo.memoria.jogo_da_memoria.model;

import javax.swing.JButton; // Importando JButton
import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private List<Card> cards;
    private List<GameBoardObserver> observers;
    private Card firstFlippedCard;
    private Card secondFlippedCard;
    private int flippedCount;
    private int attemptsLeft;
    private boolean gameWon;
    private List<JButton> observerButtons; // Lista de botões para a interface

    public GameBoard(CardFactory cardFactory) {
        this.cards = cardFactory.createCards(); // Usando o CardFactory para criar as cartas
        this.observers = new ArrayList<>();
        this.firstFlippedCard = null;
        this.secondFlippedCard = null;
        this.flippedCount = 0;
        this.attemptsLeft = 6; // Número de tentativas
        this.gameWon = false;
        this.observerButtons = new ArrayList<>();
    }

    // Adiciona um observer (controller)
    public void addObserver(GameBoardObserver observer) {
        observers.add(observer);
    }

    // Adiciona um botão para o observer
    public void addObserverButton(JButton button) {
        observerButtons.add(button);
    }

    // Método para virar uma carta
    public void flipCard(int index) {
        // Evita virar cartas já viradas ou se o jogo acabou
        Card card = cards.get(index);
        if (card.isFlipped() || attemptsLeft <= 0 || gameWon) {
            return; // Ignora se já estiver virada ou o jogo acabou
        }

        card.flip(); // Vira a carta
        if (firstFlippedCard == null) {
            firstFlippedCard = card;
        } else {
            secondFlippedCard = card;
            // Verifica se as cartas formaram um par
            boolean matchFound = firstFlippedCard.getValue() == secondFlippedCard.getValue();
            notifyObservers(matchFound);

            if (!matchFound) {
                // Se não houver correspondência, inverte as cartas após um tempo
                resetFlippedCards();
            } else {
                flippedCount += 2; // Atualiza o contador de cartas viradas corretamente
                if (flippedCount == cards.size()) {
                    gameWon = true; // O jogo foi ganho quando todas as cartas foram viradas
                }
            }
            attemptsLeft--; // Diminui as tentativas restantes
        }
    }

    // Método para reverter as cartas após 1 segundo
    private void resetFlippedCards() {
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Espera 1 segundo antes de virar as cartas de volta
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            firstFlippedCard.flip(); // Vira as cartas de volta
            secondFlippedCard.flip();
            firstFlippedCard = null; // Reseta as cartas viradas
            secondFlippedCard = null;
            notifyObservers(false); // Informa que não houve par
        }).start();
    }

    // Método para notificar os observers (por exemplo, o controlador)
    private void notifyObservers(boolean matchFound) {
        for (GameBoardObserver observer : observers) {
            observer.update(matchFound);
        }
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getFirstFlippedCard() {
        return firstFlippedCard;
    }

    public Card getSecondFlippedCard() {
        return secondFlippedCard;
    }

    // Adiciona os botões observados
    public List<JButton> getObserverButtons() {
        return observerButtons;
    }
}

