package com.jsamkt.learn.solid.lsp.bad;

public class SquareBad extends RectangleBad {
    public SquareBad(int side) {
        super(side, side);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width); // Violates LSP - changes behavior of parent class
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height); // Violates LSP - changes behavior of parent class
    }
}