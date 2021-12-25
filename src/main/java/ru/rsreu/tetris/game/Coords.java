package ru.rsreu.tetris.game;

public record Coords(int x, int y) {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
