package ru.rsreu.tetris.game;

import javafx.scene.paint.Color;

import java.util.Random;

public class Figure {
    private final FigureForm form;
    private final Color color;
    private Coords metaCoords;
    private RotationMode currentRotation;

    public Figure(Coords metaCoords, RotationMode currentRotation, FigureForm form, ColorBundle colorBundle) {
        this.metaCoords = metaCoords;
        this.currentRotation = currentRotation;
        this.form = form;

        Color[] colors = colorBundle.getColors();
        this.color = colors[new Random().nextInt(colors.length)];
    }

    public Figure(Coords metaCoords, ColorBundle colorBundle) {
        this(metaCoords, RotationMode.NORMAL, FigureForm.getRandomForm(), colorBundle);
    }

    public Color getColor() {
        return this.color;
    }

    public FigureForm getForm() {
        return form;
    }

    public void setMetaCoords(Coords metaCoords) {
        this.metaCoords = metaCoords;
    }

    public Coords[] getCoords() {
        return form.getMask().generateFigure(metaCoords, currentRotation);
    }

    public Coords[] getRotatedCoords() {
        return form.getMask().generateFigure(metaCoords, RotationMode.getNext(currentRotation));
    }

    public void rotate() {
        this.currentRotation = RotationMode.getNext(this.currentRotation);
    }

    public Coords[] getShiftedCoords(ShiftDirection direction) {
        Coords newMetaCoords = getNewMetaCoords(direction);
        return form.getMask().generateFigure(newMetaCoords, this.currentRotation);
    }

    public void shift(ShiftDirection direction) {
        this.metaCoords = getNewMetaCoords(direction);
    }

    public Coords[] getFallenCoords() {
        Coords newMetaCoords = new Coords(metaCoords.getX(), metaCoords.getY() - 1);
        return form.getMask().generateFigure(newMetaCoords, currentRotation);
    }

    public void fall() {
        metaCoords = new Coords(metaCoords.getX(), metaCoords.getY() - 1);
    }

    private Coords getNewMetaCoords(ShiftDirection direction) {
        Coords newMetaCoords = null;
        int x = this.metaCoords.getX();
        int y = this.metaCoords.getY();
        switch (direction) {
            case LEFT -> newMetaCoords = new Coords(x - 1, y);
            case RIGHT -> newMetaCoords = new Coords(x + 1, y);
        }
        return newMetaCoords;
    }
}
