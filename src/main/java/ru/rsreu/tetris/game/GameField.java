package ru.rsreu.tetris.game;

import java.util.Objects;
import java.util.Random;

public class GameField {
    public static final int COUNT_CELLS_X = 10;
    public static final int COUNT_CELLS_Y = 20;
    public static final int OFFSET_TOP = 2;

    private final Block[][] field;
    private final int[] countFilledCells;
    private Figure figure;
    private Figure nextFigure;
    private Figure stashFigure = null;
    private boolean canStash = true;
    private int score;
    private final ColorBundle colorBundle;

    public GameField(ColorBundle colorBundle) {
        this.colorBundle = colorBundle;
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

    public Figure getStashFigure() {
        return stashFigure;
    }

    public Figure getNextFigure() {
        return nextFigure;
    }

    private void spawnNewFigure() {
        this.figure = Objects.requireNonNullElseGet(nextFigure, this::getNewFigure);
        this.nextFigure = getNewFigure();
        this.canStash = true;
    }

    private Figure getNewFigure(){
        int randomX = new Random().nextInt(COUNT_CELLS_X - 4);
        return new Figure(new Coords(randomX, COUNT_CELLS_Y + OFFSET_TOP - 1), this.colorBundle);
    }

    public boolean isNotEmpty(int x, int y) {
        return (!field[x][y].equals(Block.EMPTY));
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
                    || isNotEmpty(x, y)) {
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
        this.score += COUNT_CELLS_X;
        return true;
    }

    private void shiftLinesDown() {
        int fallTo = getFirstEmptyLineId();
        int fallFrom = getFirstNoEmptyLineId(fallTo);
        while (fallTo != -1 && fallFrom != -1) {
            fallLineDown(fallFrom, fallTo);
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

    private void fallLineDown(int fallFrom, int fallTo) {
            for (int x = 0; x < COUNT_CELLS_X; x++) {
                field[x][fallTo] = field[x][fallFrom];
                field[x][fallFrom] = Block.EMPTY;
            }
            countFilledCells[fallTo] = countFilledCells[fallFrom];
            countFilledCells[fallFrom] = 0;
    }

    public boolean isOverfilled() {
        for (int i = 0; i < OFFSET_TOP; i++) {
            if (countFilledCells[COUNT_CELLS_Y + i] != 0) {
                return true;
            }
        }
        return false;
    }


    public int getScore() {
        return this.score;
    }

    public void stashFigure() {
        if(!canStash){
            return;
        }
        if(stashFigure != null){
            Figure temp = figure;
            this.figure = this.stashFigure;
            this.stashFigure = temp;
        } else {
            this.stashFigure = this.figure;
            this.figure = this.nextFigure;
            this.nextFigure = getNewFigure();
        }
        figure.setMetaCoords(new Coords(3,COUNT_CELLS_Y + OFFSET_TOP - 1));
        canStash = false;
    }
}
