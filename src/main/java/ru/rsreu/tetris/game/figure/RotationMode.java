package ru.rsreu.tetris.game.figure;

public enum RotationMode {
    NORMAL(0),
    ONE_FLIP(1),
    TWO_FLIP(2),
    THREE_FLIP(3);

    private static final RotationMode[] modes = {NORMAL, ONE_FLIP, TWO_FLIP, THREE_FLIP};
    private final int number;

    RotationMode(int number) {
        this.number = number;
    }

    public static RotationMode getNext(RotationMode mode) {
        int nextIndex = (mode.number + 1) % modes.length;
        return modes[nextIndex];
    }
}
