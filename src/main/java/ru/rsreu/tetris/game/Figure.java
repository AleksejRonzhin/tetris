package ru.rsreu.tetris.game;

import javafx.scene.paint.Color;

public class Figure {
    private Coords metaCoords;
    private RotationMode currentRotation;
    private FigureForm form;

    public Figure(Coords metaCoords, RotationMode currentRotation, FigureForm form) {
        this.metaCoords = metaCoords;
        this.currentRotation = currentRotation;
        this.form = form;
    }

    public Figure(Coords metaCoords){
        this(metaCoords, RotationMode.NORMAL, FigureForm.getRandomForm());
    }

    public Color getColor(){
        return form.getColor();
    }

    public Coords[] getCoords(){
        return form.getMask().generateFigure(metaCoords, currentRotation);
    }

    public Coords[] getRotatedCoords(){
        return form.getMask().generateFigure(metaCoords, RotationMode.getNext(currentRotation));
    }

    public void rotate(){
        this.currentRotation = RotationMode.getNext(this.currentRotation);
    }

    public Coords[] getShiftedCoords(ShiftDirection direction) {
        Coords newMetaCoords = getNewMetaCoords(direction);
        return form.getMask().generateFigure(newMetaCoords, this.currentRotation);
    }

    public void shift(ShiftDirection direction){
        this.metaCoords = getNewMetaCoords(direction);
    }

    public Coords[] getFallenCoords(){
        Coords newMetaCoords = new Coords(metaCoords.getX() - 1, metaCoords.getY());
        return form.getMask().generateFigure(newMetaCoords, currentRotation);
    }

    public void fall(){
        metaCoords = new Coords(metaCoords.getX(), metaCoords.getY() - 1);
    }

    private Coords getNewMetaCoords(ShiftDirection direction){
        Coords newMetaCoords = null;
        int x = this.metaCoords.getX();
        int y = this.metaCoords.getY();
        switch (direction){
            case LEFT -> {
                newMetaCoords = new Coords(x - 1, y);
            }
            case RIGHT -> {
                newMetaCoords = new Coords(x + 1, y);
            }
        }
        return  newMetaCoords;
    }
}
