package ru.rsreu.tetris.game.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ru.rsreu.tetris.game.ShiftDirection;

public class StageKeyboardHandleModule implements KeyboardHandleModule{
    Scene scene;
    private boolean wasEscPressed = false;
    private boolean wasRotateRequested = false;
    private ShiftDirection shiftDirection = ShiftDirection.NONE;
    private boolean wasBoostRequested = false;

    public StageKeyboardHandleModule(Scene scene){
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
        return this.wasBoostRequested;
    }

    @Override
    public void clean() {
        wasEscPressed = false;
        wasRotateRequested = false;
        shiftDirection = ShiftDirection.NONE;
        wasBoostRequested = false;
    }

    @Override
    public void update() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case ESCAPE:
                    wasEscPressed = true;
                    break;
                case A:
                    shiftDirection = ShiftDirection.LEFT;
                    break;
                case D:
                    shiftDirection = ShiftDirection.RIGHT;
                    break;
                case W:
                    wasRotateRequested = true;
                    break;
                case S:
                    wasBoostRequested = true;
                    break;
            }
        });
    }
}
