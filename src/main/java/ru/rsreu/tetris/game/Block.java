package ru.rsreu.tetris.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Block extends Shape {
    Color color;

    public Block(Color color){
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }
}
