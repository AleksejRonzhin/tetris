package ru.rsreu.tetris.game;

import javafx.scene.paint.Color;

import java.util.Random;

public enum FigureForm {
    I_FORM(CoordsMask.I_FORM, Color.RED),
    O_FORM(CoordsMask.O_FORM, Color.PURPLE),
    T_FORM(CoordsMask.T_FORM, Color.LIGHTBLUE),
    J_FORM(CoordsMask.J_FORM, Color.BLUE),
    L_FORM(CoordsMask.L_FORM, Color.GREEN),
    S_FORM(CoordsMask.S_FORM, Color.PINK),
    Z_FORM(CoordsMask.Z_FORM, Color.ORANGE);

    private final CoordsMask mask;
    private final Color color;

    private static final FigureForm[] forms = {I_FORM, O_FORM, S_FORM, Z_FORM, T_FORM, J_FORM, L_FORM};

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
        return this.color;
    }
}
