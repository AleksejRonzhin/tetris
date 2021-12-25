package ru.rsreu.tetris;

import javafx.scene.paint.Color;

import java.util.ResourceBundle;

public enum ColorTheme {
    LIGHT("ru/rsreu/tetris/stylesheet.css", "light",
            new Color[]{Color.web("#605BF0"), Color.web("#4FF091"), Color.web("#F05A4F"), Color.web("#F0DD7F")},
            Color.web("#fdfcdc"), Color.web("#f07167")),
    DARK("ru/rsreu/tetris/dark_stylesheet.css", "dark",
            new Color[]{Color.LIGHTBLUE, Color.PINK, Color.ORANGE},
            Color.web("#261C2C"), Color.web("#6E85B2"));

    public static ColorTheme[] themes = {LIGHT, DARK};
    private static ResourceBundle bundle = ResourceBundle.getBundle("ru/rsreu/tetris/bundle", Language.DEFAULT.getLocale());
    private final String path;
    private final String text;
    private final Color[] figuresColors;
    private final Color backgroundColor;
    private final Color textColor;

    ColorTheme(String path, String text, Color[] figuresColors, Color backgroundColor, Color textColor) {
        this.path = path;
        this.text = text;
        this.figuresColors = figuresColors;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    public static void updateBundle(Language language) {
        bundle = ResourceBundle.getBundle("ru/rsreu/tetris/bundle", language.getLocale());
    }

    public String getPath() {
        return path;
    }

    public Color[] getFiguresColors() {
        return figuresColors;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    @Override
    public String toString() {
        return bundle.getString(this.text);
    }
}
