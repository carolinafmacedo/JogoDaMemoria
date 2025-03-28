package com.jogo.memoria.jogo_da_memoria.model;

public class SingletonGameBoard {
    private static GameBoard gameBoard;

    // Construtor privado para impedir criação de instâncias externas
    private SingletonGameBoard() {
    }

    // Método para obter a única instância do GameBoard
    public static GameBoard getInstance(CardFactory cardFactory) {
        if (gameBoard == null) {
            gameBoard = new GameBoard(cardFactory); // Passando a fábrica para o GameBoard
        }
        return gameBoard;
    }
}
