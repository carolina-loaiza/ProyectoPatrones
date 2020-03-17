package com.graphics.Board;

public class Dice {
    private int max;
    private int min;
    private int range;
    private int value;

    public Dice() {
        this.max = 6;
        this.min = 1;
        this.range = max - min + 1;
    }

    public int getNewValue() {
        this.value = (int) (Math.random() * this.range) + this.min;
        return value;
    }

    public int getValue() {
        return value;
    }
}
