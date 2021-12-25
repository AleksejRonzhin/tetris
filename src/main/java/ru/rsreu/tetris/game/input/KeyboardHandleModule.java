package ru.rsreu.tetris.game.input;

import ru.rsreu.tetris.game.figure.ShiftDirection;

public interface KeyboardHandleModule {
    void update();

    boolean wasEscPressed();

    ShiftDirection getShiftDirection();

    boolean wasRotateRequested();

    boolean wasBoostRequested();

    boolean wasStashRequested();

    void clean();
}
