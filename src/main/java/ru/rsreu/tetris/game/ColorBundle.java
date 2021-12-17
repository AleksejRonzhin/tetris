package ru.rsreu.tetris.game;

import javafx.scene.paint.Color;
import ru.rsreu.tetris.ColorTheme;

public class ColorBundle {
    private final Color[] colors;
    private final Color backgroundColor;
    private final Color textColor;

    public ColorBundle(ColorTheme theme) {
        this.colors = theme.getFiguresColors();
        this.backgroundColor = theme.getBackgroundColor();
        this.textColor = theme.getTextColor();
    }

    public Color[] getColors() {
        return colors;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }
}
