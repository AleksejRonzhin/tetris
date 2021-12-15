package ru.rsreu.tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import ru.rsreu.tetris.game.Game;

public class GameController {
    @FXML
    public Button btnStop;
    @FXML
    public Button btnContinue;
    @FXML
    private Button btnStart;
    @FXML
    private Canvas gameCanvas;

    private Game game;

    @FXML
    public void btnStartOnAction(ActionEvent actionEvent) {
        this.game = new Game(this.gameCanvas);
        this.game.start();
        this.btnStart.setVisible(false);
        this.btnStop.setVisible(true);
    }

    @FXML
    public void btnStopOnAction(ActionEvent actionEvent) {
        this.game.stop();
        this.btnStop.setVisible(false);
        this.btnContinue.setVisible(true);
    }

    @FXML
    public void btnContinueOnAction(ActionEvent actionEvent) {
        this.game.start();
        this.btnStop.setVisible(true);
        this.btnContinue.setVisible(false);
    }
}
