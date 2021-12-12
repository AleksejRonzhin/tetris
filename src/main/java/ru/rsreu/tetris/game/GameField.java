package ru.rsreu.tetris.game;

import java.util.Random;

public class GameField {
    public static final int COUNT_CELLS_X = 10;
    public static final int COUNT_CELLS_Y = 20;
    public static final int OFFSET_TOP = 2;

    private final Block[][] field;
    private final int[] countFilledCells;
    private Figure figure;

    public GameField() {
        spawnNewFigure();
        field = new Block[COUNT_CELLS_X][COUNT_CELLS_Y + OFFSET_TOP];
        countFilledCells = new int[COUNT_CELLS_Y + OFFSET_TOP];
        for (int x = 0; x < COUNT_CELLS_X; x++) {
            for (int y = 0; y < COUNT_CELLS_Y + OFFSET_TOP; y++) {
                field[x][y] = Block.EMPTY;
            }
        }
        for (int y = 0; y < COUNT_CELLS_Y + OFFSET_TOP; y++) {
            countFilledCells[y] = 0;
        }
    }

    public Block getBlock(int x, int y) {
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
        return (field[x][y].equals(Block.EMPTY));
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
            field[coords.getX()][coords.getY()] = new Block(figure.getColor());
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
            field[x][y] = Block.EMPTY;
        }
        countFilledCells[y] = 0;
        return true;
    }

    private void shiftLinesDown() {
        int fallTo = getFirstEmptyLineId();
        int fallFrom = getFirstNoEmptyLineId(fallTo);
        while (fallTo != -1 && fallFrom != -1) {
            fallLinesDown(fallFrom, fallTo);
            fallTo = getFirstEmptyLineId();
            fallFrom = getFirstNoEmptyLineId(fallTo);
        }
    }

    private int getFirstEmptyLineId() {
        for (int y = 0; y < COUNT_CELLS_Y; y++) {
            if (countFilledCells[y] == 0) {
                return y;
            }
        }
        return -1;
    }

    private int getFirstNoEmptyLineId(int minId) {
        for (int y = minId + 1; y < COUNT_CELLS_Y; y++) {
            if (countFilledCells[y] != 0) {
                return y;
            }
        }
        return -1;
    }

    private void fallLinesDown(int fallFrom, int fallTo) {
        for (int y = fallFrom; y < COUNT_CELLS_Y; y++) {
            for (int x = 0; x < COUNT_CELLS_X; x++) {
                field[x][fallTo] = field[x][y];
                field[x][y] = Block.EMPTY;
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
