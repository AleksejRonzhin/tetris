package ru.rsreu.tetris;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("ru/rsreu/tetris/bundle", new Locale("en"));
        URL location = Application.class.getResource("main-menu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location, bundle);
        fxmlLoader.setControllerFactory(param -> new Controller(stage));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}