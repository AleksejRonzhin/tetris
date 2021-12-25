package ru.rsreu.tetris;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Language {
    RU(new Locale("ru", "RU"), "russian"),
    EN(new Locale("en"), "english"),
    DEFAULT(Locale.getDefault(), "default");

    public static final Language[] languages = {RU, EN};
    private static ResourceBundle bundle = ResourceBundle.getBundle("ru/rsreu/tetris/bundle", DEFAULT.getLocale());

    private final Locale locale;
    private final String title;

    public static void updateBundle(Language language) {
        bundle = ResourceBundle.getBundle("ru/rsreu/tetris/bundle", language.getLocale());
    }

    Language(Locale locale, String title) {
        this.locale = locale;
        this.title = title;
    }

    public Locale getLocale() {
        return locale;
    }

    @Override
    public String toString() {
        return bundle.getString(title);
    }
}
