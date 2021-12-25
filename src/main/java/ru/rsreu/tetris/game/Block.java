package ru.rsreu.tetris.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
    public static final Block EMPTY = new Block(Color.TRANSPARENT);

    private final Color color;

    public Block(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
