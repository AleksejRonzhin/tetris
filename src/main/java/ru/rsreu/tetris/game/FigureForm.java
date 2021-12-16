package ru.rsreu.tetris.game;

import java.util.Random;


public enum FigureForm {
    I_FORM(CoordsMask.I_FORM),
    O_FORM(CoordsMask.O_FORM),
    T_FORM(CoordsMask.T_FORM),
    J_FORM(CoordsMask.J_FORM),
    L_FORM(CoordsMask.L_FORM),
    S_FORM(CoordsMask.S_FORM),
    Z_FORM(CoordsMask.Z_FORM);

    private static final FigureForm[] forms = {I_FORM, O_FORM, S_FORM, Z_FORM, T_FORM, J_FORM, L_FORM};
    private final CoordsMask mask;

    FigureForm(CoordsMask mask) {
        this.mask = mask;
    }

    public static FigureForm getRandomForm() {
        int number = new Random().nextInt(forms.length);
        return forms[number];
    }

    public CoordsMask getMask() {
        return this.mask;
    }

}
