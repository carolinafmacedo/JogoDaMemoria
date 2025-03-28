package com.jogo.memoria.jogo_da_memoria.model;

public class Card {
    private int value;
    private boolean flipped;

    public Card(int value) {
        this.value = value;
        this.flipped = false;
    }

    public int getValue() {
        return value;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void flip() {
        flipped = !flipped;
    }
}


