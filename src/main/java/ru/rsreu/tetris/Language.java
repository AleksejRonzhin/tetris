package ru.rsreu.tetris;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public enum Language {
    RU(new Locale("ru", "RU")),
    EN(new Locale("en")),
    DEFAULT(Locale.getDefault());

    public static final Language[] languages = {RU, EN};

    private final Locale locale;

    Language(Locale locale){
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}
