package ru.rsreu.tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {
    private Stage stage;


    public Controller(Stage stage){
        this.stage = stage;
    }

    @FXML
    public void btnStartOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        URL location = Application.class.getResource("game.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        this.stage.hide();
        stage.showAndWait();
        this.stage.show();
    }

    @FXML
    public void btnSettingsOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("ru/rsreu/tetris/bundle", new Locale("en"));
        URL location = Application.class.getResource("settings.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location, bundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        this.stage.hide();
        stage.showAndWait();
        this.stage.show();
    }
}