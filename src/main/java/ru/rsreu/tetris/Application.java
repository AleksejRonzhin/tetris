package ru.rsreu.tetris;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.rsreu.tetris.game.ColorBundle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Application extends javafx.application.Application {
    private ColorTheme colorTheme = ColorTheme.LIGHT;
    private Language language = Language.DEFAULT;
    private ResourceBundle bundle;
    private Stage stage = null;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.bundle = ResourceBundle.getBundle("ru/rsreu/tetris/bundle", language.getLocale());
        Language.updateBundle(language);
        ColorTheme.updateBundle(language);
        this.stage = stage;
        stage.getIcons().add(new Image("ru/rsreu/tetris/icons/icon.png"));
        URL location = Application.class.getResource("main-menu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location, bundle);
        fxmlLoader.setControllerFactory(param -> new Controller(this));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public Stage getStage() {
        return stage;
    }

    public void restart() throws Exception {
        stop();
        start(this.stage);
    }
}