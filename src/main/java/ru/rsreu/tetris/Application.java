package ru.rsreu.tetris;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Application extends javafx.application.Application {
    private String stylesheet = "ru/rsreu/tetris/stylesheet.css";
    private ResourceBundle bundle = ResourceBundle.getBundle("ru/rsreu/tetris/bundle", Locale.getDefault());

    @Override
    public void start(Stage stage) throws IOException {

        URL location = Application.class.getResource("main-menu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location, bundle);
        fxmlLoader.setControllerFactory(param -> new Controller(stage, this));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public String getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(String stylesheet) {
        this.stylesheet = stylesheet;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public static void main(String[] args) {
        launch();
    }
}