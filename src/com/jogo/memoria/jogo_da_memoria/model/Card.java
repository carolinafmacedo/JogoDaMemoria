package com.jogo.memoria.jogo_da_memoria.model;

public class Card {
    private int value;
    private boolean flipped;
    private boolean matched;

    public Card(int value) {
        this.value = value;
        this.flipped = false;
        this.matched = false;
    }

    public int getValue() {
        return value;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}


