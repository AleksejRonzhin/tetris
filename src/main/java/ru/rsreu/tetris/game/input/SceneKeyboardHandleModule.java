package ru.rsreu.tetris.game.input;

import javafx.scene.Scene;
import ru.rsreu.tetris.game.figure.ShiftDirection;

public class SceneKeyboardHandleModule implements KeyboardHandleModule {
    Scene scene;
    private boolean wasEscPressed = false;
    private boolean wasRotateRequested = false;
    private ShiftDirection shiftDirection = ShiftDirection.NONE;
    private boolean wasBoostRequested = false;
    private boolean wasStashRequested = false;

    public SceneKeyboardHandleModule(Scene scene) {
        this.scene = scene;
    }

    @Override
    public boolean wasEscPressed() {
        return this.wasEscPressed;
    }

    @Override
    public ShiftDirection getShiftDirection() {
        return this.shiftDirection;
    }

    @Override
    public boolean wasRotateRequested() {
        return this.wasRotateRequested;
    }

    @Override
    public boolean wasBoostRequested() {
        return wasBoostRequested;
    }

    @Override
    public boolean wasStashRequested() {
        return this.wasStashRequested;
    }

    @Override
    public void clean() {
        wasEscPressed = false;
        wasRotateRequested = false;
        shiftDirection = ShiftDirection.NONE;
        wasBoostRequested = false;
        wasStashRequested = false;
    }

    @Override
    public void update() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE -> wasEscPressed = true;
                case A, LEFT -> shiftDirection = ShiftDirection.LEFT;
                case D, RIGHT -> shiftDirection = ShiftDirection.RIGHT;
                case W, UP -> wasRotateRequested = true;
                case S, DOWN -> wasBoostRequested = true;
                case C -> wasStashRequested = true;
            }
        });
    }
}
