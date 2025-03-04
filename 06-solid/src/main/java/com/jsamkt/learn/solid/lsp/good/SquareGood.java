package com.jsamkt.learn.solid.lsp.good;

public class SquareGood implements ShapeGood {
    private int side;

    public SquareGood(int side) {
        this.side = side;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    public int calculateArea() {
        return side * side;
    }
}
