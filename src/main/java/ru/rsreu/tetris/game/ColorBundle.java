package ru.rsreu.tetris.game;

import javafx.scene.paint.Color;
import ru.rsreu.tetris.ColorTheme;

public class ColorBundle {
    private static Color[] colors;
    private static Color backgroundColor;

    public static void setTheme(ColorTheme theme) {
        colors = theme.getFiguresColors();
        backgroundColor = theme.getBackgroundColor();
    }

    public static Color[] getColors() {
        return colors;
    }

    public static Color getBackgroundColor() {
        return backgroundColor;
    }
}
