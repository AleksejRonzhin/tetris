package ru.rsreu.tetris.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.Random;

public class Game {
    private static final int ROWS = 10;
    private static final int BLOCK_SIZE = 20;
    private static final int COLUMNS = 8;
    private final Block[][] blocks;
    private final Canvas canvas;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;
    private int current_i = 0;
    private int current_j = 0;

    public Game(Canvas canvas) {
        blocks = new Block[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                blocks[i][j] = new Block(Color.TRANSPARENT);
            }
        }
        this.canvas = canvas;
    }

    public void print() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Block block = blocks[i][j];
                gc.setFill(block.getColor());
                gc.fillRect(i * BLOCK_SIZE, j * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public void start() {
        Scene scene = canvas.getScene();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::press);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, this::release);

        Block block = new Block(Color.RED);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (upPressed) {
                    if(current_j > 0){
                        current_j--;
                    }
                    upPressed = false;
                }
                if (downPressed) {
                    if(current_j < COLUMNS - 1){
                        current_j++;
                    }
                    downPressed = false;
                }
                if (leftPressed) {
                    if(current_i > 0){
                        current_i--;
                    }
                    leftPressed = false;
                }
                if (rightPressed) {
                    if(current_i < ROWS - 1){
                        current_i++;
                    }
                    rightPressed = false;
                }
                blocks[current_i][current_j] = block;
                print();
            }
        };
        animationTimer.start();
    }

    private void press(KeyEvent event) {
        handle(event.getCode(), true);
        System.out.println(event.toString());
    }

    private void release(KeyEvent event) {
        handle(event.getCode(), false);
    }

    private void handle(KeyCode key, boolean press) {
        if (key == KeyCode.W) {
            upPressed = press;
        }
        if (key == KeyCode.S) {
            downPressed = press;
        }
        if (key == KeyCode.A) {
            leftPressed = press;
        }
        if (key == KeyCode.D) {
            rightPressed = press;
        }
    }
}
