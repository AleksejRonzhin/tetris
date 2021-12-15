package ru.rsreu.tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingController {

    private final String stylesheet;
    private Application application;
    private Stage stage;
    @FXML
    public BorderPane bpSettings;

    public SettingController(String stylesheet, Application application, Stage stage) {
        this.stylesheet = stylesheet;
        this.application = application;
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        this.bpSettings.getStylesheets().add(this.stylesheet);
    }

    public void btnOnAction(ActionEvent actionEvent) {
        this.application.setStylesheet("ru/rsreu/tetris/stylesheet.css");
    }

    public void btnDarkOnAction(ActionEvent actionEvent) {
        this.application.setStylesheet("ru/rsreu/tetris/dark_stylesheet.css");
    }
}
