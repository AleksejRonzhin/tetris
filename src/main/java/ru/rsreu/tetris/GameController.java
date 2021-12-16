package ru.rsreu.tetris;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import ru.rsreu.tetris.game.ColorBundle;
import ru.rsreu.tetris.game.Game;

public class GameController {
    @FXML
    public Button btnStop;
    @FXML
    public Button btnContinue;
    @FXML
    public Label lblScore;
    @FXML
    public BorderPane bpMain;
    @FXML
    private Button btnStart;
    @FXML
    private Canvas gameCanvas;
    private final Application application;
    private Game game;

    public GameController(Application application) {
        this.application = application;
    }

    @FXML
    public void initialize() {
        ColorTheme theme = application.getColorTheme();
        this.bpMain.getStylesheets().add(theme.getPath());
    }

    @FXML
    public void btnStartOnAction() {
        this.game = new Game(this.gameCanvas, application.getBundle(), new ColorBundle(application.getColorTheme()));
        this.game.start();
        this.btnStart.setVisible(false);
        this.btnStop.setVisible(true);
    }

    @FXML
    public void btnStopOnAction() {
        this.game.stop();
        this.btnStop.setVisible(false);
        this.btnContinue.setVisible(true);
    }

    @FXML
    public void btnContinueOnAction() {
        this.game.start();
        this.btnStop.setVisible(true);
        this.btnContinue.setVisible(false);
    }
}
