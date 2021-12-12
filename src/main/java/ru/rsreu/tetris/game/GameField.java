package ru.rsreu.tetris.game;

import javafx.scene.paint.Color;

import java.util.Random;

public class GameField {
    public static final int COUNT_CELLS_X = 10;
    public static final int COUNT_CELLS_Y = 20;
    public static final int OFFSET_TOP = 2;

    private final Color[][] field;
    private final int[] countFilledCells;
    private Figure figure;

    public GameField() {
        spawnNewFigure();
        field = new Color[COUNT_CELLS_X][COUNT_CELLS_Y + OFFSET_TOP];
        countFilledCells = new int[COUNT_CELLS_Y + OFFSET_TOP];
        for (int x = 0; x < COUNT_CELLS_X; x++) {
            for (int y = 0; y < COUNT_CELLS_Y + OFFSET_TOP; y++) {
                field[x][y] = Color.WHITE;
            }
        }
        for (int y = 0; y < COUNT_CELLS_Y + OFFSET_TOP; y++) {
            countFilledCells[y] = 0;
        }
    }

    public Color getColor(int x, int y) {
        return field[x][y];
    }

    public Figure getFigure() {
        return this.figure;
    }

    private void spawnNewFigure() {
        int randomX = new Random().nextInt(COUNT_CELLS_X - 4);
        this.figure = new Figure(new Coords(randomX, COUNT_CELLS_Y + OFFSET_TOP - 1));
    }

    public boolean isEmpty(int x, int y) {
        return (field[x][y].equals(Color.WHITE));
    }

    public void tryShiftFigure(ShiftDirection direction) {
        Coords[] shiftedCoords = figure.getShiftedCoords(direction);
        if (isRightCoords(shiftedCoords)) {
            figure.shift(direction);
        }
    }

    private boolean isRightCoords(Coords[] shiftedCoords) {
        for (Coords coords : shiftedCoords) {
            int x = coords.getX();
            int y = coords.getY();
            if (x < 0 || x >= COUNT_CELLS_X
                    || y < 0 || y >= COUNT_CELLS_Y + OFFSET_TOP
                    || !isEmpty(x, y)) {
                return false;
            }
        }
        return true;
    }

    public void tryRotateFigure() {
        Coords[] rotatedCoords = figure.getRotatedCoords();
        if (isRightCoords(rotatedCoords)) {
            figure.rotate();
        }
    }

    public void letFallDown() {
        Coords[] fallenCoords = figure.getFallenCoords();
        if (isRightCoords(fallenCoords)) {
            figure.fall();
        } else {
            stopFigure();
        }
    }

    private void stopFigure() {
        Coords[] figureCoords = figure.getCoords();
        boolean haveToShiftLinesDown = false;
        for (Coords coords : figureCoords) {
            field[coords.getX()][coords.getY()] = figure.getColor();
            countFilledCells[coords.getY()]++;
            haveToShiftLinesDown = tryDestroyLine(coords.getY()) || haveToShiftLinesDown;
        }
        if (haveToShiftLinesDown) {
            shiftLinesDown();
        }
        spawnNewFigure();
    }

    private boolean tryDestroyLine(int y) {
        if (countFilledCells[y] < COUNT_CELLS_X) {
            return false;
        }
        for (int x = 0; x < COUNT_CELLS_X; x++) {
            field[x][y] = Color.WHITE;
        }
        countFilledCells[y] = 0;
        return true;
    }

    private void shiftLinesDown() {
        try {
            int fallTo = getFirstEmptyLineId();
            int fallFrom = getFirstNoEmptyLineId(fallTo);
            fallLinesDown(fallFrom, fallTo);
        } catch (Exception ignored) {

        }
    }

    private int getFirstEmptyLineId() throws Exception {
        for (int y = 0; y < COUNT_CELLS_Y; y++) {
            if (countFilledCells[y] == 0) {
                return y;
            }
        }
        throw new Exception();
    }

    private int getFirstNoEmptyLineId(int minId) throws Exception {
        for (int y = minId + 1; y < COUNT_CELLS_Y; y++) {
            if (countFilledCells[y] != 0) {
                return y;
            }
        }
        throw new Exception();
    }

    private void fallLinesDown(int fallFrom, int fallTo) {
        for (int y = fallFrom; y < COUNT_CELLS_Y; y++) {
            for (int x = 0; x < COUNT_CELLS_X; x++) {
                field[x][fallTo] = field[x][y];
                field[x][y] = Color.WHITE;
            }
            countFilledCells[fallTo] = countFilledCells[y];
            countFilledCells[y] = 0;
            fallTo++;
        }
    }

    public boolean isOverfilled() {
        for (int i = 0; i < OFFSET_TOP; i++) {
            if (countFilledCells[COUNT_CELLS_Y + i] != 0) {
                return true;
            }
        }
        return false;
    }
}
