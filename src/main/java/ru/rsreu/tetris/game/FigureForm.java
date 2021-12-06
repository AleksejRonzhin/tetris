package ru.rsreu.tetris.game;

import javafx.scene.paint.Color;

import java.util.Random;

public enum FigureForm {
    I_FORM(CoordsMask.I_FORM, Color.BLACK);

    private CoordsMask mask;
    private Color color;

    private static final FigureForm[] forms = {I_FORM, I_FORM};

    public static FigureForm getRandomForm(){
        int number = new Random().nextInt(forms.length);
        return forms[number];
    }

    FigureForm(CoordsMask mask, Color color){
        this.mask = mask;
        this.color = color;
    }

    public CoordsMask getMask(){
        return this.mask;
    }

    public Color getColor() {
        return color;
    }
}
