package com.graphics.Board.Cell;

public class Devil extends Cell {
    public Devil(int positionX, int positionY, int number) {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        this.setNumber(number);
        this.setType(CellType.Devil);
    }

    @Override
    public Cell clone() {
        return new Devil(this.getPositionX(), this.getPositionY(), this.getNumber());
    }
}
