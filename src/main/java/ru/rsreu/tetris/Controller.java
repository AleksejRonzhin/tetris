package ru.rsreu.tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Controller {
    @FXML
    public BorderPane bpMainMenu;
    private final Application application;

    public Controller(Application application) {
        this.application = application;
    }

    @FXML
    public void initialize(){
        bpMainMenu.getStylesheets().add(application.getColorTheme().getPath());
    }

    @FXML
    public void btnStartOnAction(ActionEvent actionEvent) throws Exception {
        URL location = Application.class.getResource("game.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location, application.getBundle());
        fxmlLoader.setControllerFactory(param -> new GameController(application));
        loadForm(fxmlLoader);
    }

    @FXML
    public void btnSettingsOnAction(ActionEvent actionEvent) throws Exception {
        URL location = Application.class.getResource("settings.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location, application.getBundle());
        fxmlLoader.setControllerFactory(param -> new SettingController(application));
        loadForm(fxmlLoader);
    }

    public void loadForm(FXMLLoader fxmlLoader) throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        application.getStage().hide();
        stage.showAndWait();
        application.stop();
        application.start(stage);
    }
}