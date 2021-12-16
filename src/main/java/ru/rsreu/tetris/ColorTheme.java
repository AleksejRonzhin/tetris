package ru.rsreu.tetris;

import javafx.scene.paint.Color;

import java.util.ResourceBundle;

public enum ColorTheme {
    LIGHT("ru/rsreu/tetris/stylesheet.css", "light", new Color[]{Color.RED, Color.PURPLE, Color.GREEN, Color.BLUE}, Color.WHITE),
    DARK("ru/rsreu/tetris/dark_stylesheet.css", "dark", new Color[]{Color.LIGHTBLUE, Color.PINK, Color.ORANGE}, Color.BLACK);

    public static ColorTheme[] themes = {LIGHT, DARK};
    private static ResourceBundle bundle = ResourceBundle.getBundle("ru/rsreu/tetris/bundle", Language.DEFAULT.getLocale());
    String path;
    String text;
    Color[] figuresColors;
    Color backgroundColor;

    ColorTheme(String path, String text, Color[] figuresColors, Color backgroundColor) {
        this.path = path;
        this.text = text;
        this.figuresColors = figuresColors;
        this.backgroundColor = backgroundColor;
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

    @Override
    public String toString() {
        return bundle.getString(this.text);
    }
}
