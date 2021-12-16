package ru.rsreu.tetris;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SettingController {
    private final Application application;
    @FXML
    public BorderPane bpSettings;
    @FXML
    public Button btnSettingsSave;
    @FXML
    public Button btnSettingsCancel;
    @FXML
    public ChoiceBox<ColorTheme> colorThemeChoiceBox;
    @FXML
    public ChoiceBox<Language> languageChoiceBox;

    public SettingController(Application application) {
        this.application = application;
    }

    @FXML
    public void initialize() {
        ColorTheme theme = application.getColorTheme();
        this.bpSettings.getStylesheets().add(theme.getPath());
        colorThemeChoiceBox.setValue(theme);
        colorThemeChoiceBox.getItems().addAll(ColorTheme.themes);
        languageChoiceBox.setValue(application.getLanguage());
        languageChoiceBox.getItems().addAll(Language.languages);
    }

    public void btnSaveOnAction() {
        application.setColorTheme(colorThemeChoiceBox.getValue());
        application.setLanguage(languageChoiceBox.getValue());
        closeStage();
    }

    public void btnCancelOnAction() {
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) btnSettingsCancel.getScene().getWindow();
        stage.close();
    }
}
