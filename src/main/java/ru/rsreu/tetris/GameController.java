package ru.rsreu.tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import ru.rsreu.tetris.game.Game;

public class GameController {
    @FXML
    private Canvas gameCanvas;

    @FXML
    public void btnStartOnAction(ActionEvent actionEvent) {
        Game game = new Game();
    }
}
