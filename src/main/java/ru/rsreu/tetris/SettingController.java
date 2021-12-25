package ru.rsreu.tetris;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SettingController {
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
    @FXML
    public Slider musicSlider;

    private final Application application;

    public SettingController(Application application) {
        this.application = application;
    }

    @FXML
    public void initialize() {
        ColorTheme theme = application.getColorTheme();
        this.bpSettings.getStylesheets().add(theme.getPath());
        this.colorThemeChoiceBox.setValue(theme);
        this.colorThemeChoiceBox.getItems().addAll(ColorTheme.themes);
        this.languageChoiceBox.setValue(application.getLanguage());
        this.languageChoiceBox.getItems().addAll(Language.languages);
        this.musicSlider.setMin(0);
        this.musicSlider.setMax(100);
        this.musicSlider.setShowTickLabels(false);
        this.musicSlider.setValue(application.getPlayer().getVolume() * 100);
    }

    public void btnSaveOnAction() {
        application.setColorTheme(colorThemeChoiceBox.getValue());
        application.setLanguage(languageChoiceBox.getValue());
        application.getPlayer().setVolume(musicSlider.getValue() / 100);
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
