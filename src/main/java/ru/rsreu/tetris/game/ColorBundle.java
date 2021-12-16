package ru.rsreu.tetris.game;

import javafx.scene.paint.Color;
import ru.rsreu.tetris.ColorTheme;

public class ColorBundle {
    private Color[] colors;
    private Color backgroundColor;

    public ColorBundle(ColorTheme theme) {
        this.colors = theme.getFiguresColors();
        this.backgroundColor = theme.getBackgroundColor();
    }

    public Color[] getColors() {
        return colors;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }
}
