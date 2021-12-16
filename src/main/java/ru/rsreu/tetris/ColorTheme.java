package ru.rsreu.tetris;

import java.util.ArrayList;
import java.util.Collection;

public enum ColorTheme {
    LIGHT("ru/rsreu/tetris/stylesheet.css"),
    DARK("ru/rsreu/tetris/dark_stylesheet.css");

    String path;

    ColorTheme(String path){
        this.path = path;
    }

    public static ColorTheme[] themes = {LIGHT, DARK};

    public String getPath() {
        return path;
    }
}
