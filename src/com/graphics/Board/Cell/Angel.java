package com.graphics.Board.Cell;

public class Angel extends Cell {
    public Angel(int positionX, int positionY, int number) {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        this.setNumber(number);
        this.setType(CellType.Angel);
    }

    @Override
    public Cell clone() {
        return new Angel(this.getPositionX(), this.getPositionY(), this.getNumber());
    }
}
