package com.jogo.memoria.jogo_da_memoria.model;

import java.util.ArrayList;
import java.util.List;

import com.jogo.memoria.jogo_da_memoria.observer.GameBoardObserver;

public abstract class AbstractGameBoard {
    protected List<Card> cards; // Lista de cartas
    protected List<Integer> flippedIndices = new ArrayList<>(); // Índices das cartas viradas
    protected int attemptsLeft = 10; // Tentativas restantes
    private boolean isCheckingMatch = false; // Bloqueia novas jogadas durante a verificação

    // Lista de observadores
    private List<GameBoardObserver> observers = new ArrayList<>();

    public AbstractGameBoard(CardFactory cardFactory, int size) {
        this.cards = cardFactory.createCards(size); // Cria as cartas com base no tamanho do tabuleiro
    }

    public List<Card> getCards() {
        return cards;
    }

    // Método para adicionar observadores
    public void addObserver(GameBoardObserver observer) {
        observers.add(observer);
    }

    // Método para notificar os observadores (atualizar interface)
    public void notifyObservers() {
        for (GameBoardObserver observer : observers) {
            observer.onCardFlipped(); // Atualiza a interface gráfica
        }
    }

    // Método para virar uma carta
    public void flipCard(int index) {
        if (isCheckingMatch || flippedIndices.contains(index) || cards.get(index).isMatched()) {
            return; // Não vira a carta se já foi virada ou combinada
        }

        flippedIndices.add(index);
        cards.get(index).setFlipped(true);

        notifyObservers(); // Atualiza a interface imediatamente para exibir a carta virada

        if (flippedIndices.size() == getMaxFlippedCards()) {
            isCheckingMatch = true; // Bloqueia novos cliques
            new Thread(() -> { 
                try {
                    Thread.sleep(500); // Tempo de espera reduzido para virada das cartas (metade do tempo anterior)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkMatch();
            }).start();
        }
    }

    // Método para verificar se há uma correspondência (pares ou trios)
    protected void checkMatch() {
        boolean isMatch = isMatch(); // Chama o método isMatch implementado nas subclasses

        if (isMatch) {
            for (int index : flippedIndices) {
                cards.get(index).setMatched(true);
            }
        } else {
            try {
                Thread.sleep(500); // Tempo reduzido para mostrar erro antes de virar as cartas de volta
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int index : flippedIndices) {
                cards.get(index).setFlipped(false);
            }
            decrementAttempts();
        }

        flippedIndices.clear(); // Reseta os índices virados
        isCheckingMatch = false; // Libera para novas jogadas

        notifyObservers(); // Atualiza a interface gráfica

        if (isGameWon()) {
            System.out.println("Você venceu!");
        } else if (isGameLost()) {
            System.out.println("Você perdeu!");
        }
    }

    // Define o número máximo de cartas que podem ser viradas ao mesmo tempo (2 para pares, 3 para trios)
    protected abstract int getMaxFlippedCards();

    // Método abstrato para verificar se há uma correspondência. Deve ser implementado nas subclasses.
    protected abstract boolean isMatch();

    public boolean isGameWon() {
        for (Card card : cards) {
            if (!card.isMatched()) {
                return false;
            }
        }
        return true;
    }

    public boolean isGameLost() {
        return attemptsLeft <= 0;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public void decrementAttempts() {
        if (attemptsLeft > 0) {
            attemptsLeft--;
        }
    }
}








