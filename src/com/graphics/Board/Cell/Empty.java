package com.graphics.Board.Cell;

public class Empty extends Cell {
    public Empty(int positionX, int positionY, int number) {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        this.setNumber(number);
        this.setType(CellType.Empty);
    }

    @Override
    public Cell clone() {
        return new Empty(this.getPositionX(), this.getPositionY(), this.getNumber());
    }
}
