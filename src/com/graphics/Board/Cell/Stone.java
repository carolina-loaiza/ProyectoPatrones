package com.graphics.Board.Cell;

public class Stone extends Cell {
    public Stone(int positionX, int positionY, int number) {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        this.setNumber(number);
        this.setType(CellType.Devil);
    }

    @Override
    public Cell clone() {
        return new Stone(this.getPositionX(), this.getPositionY(), this.getNumber());
    }
}
