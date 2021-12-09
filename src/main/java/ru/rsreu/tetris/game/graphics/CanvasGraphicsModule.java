package ru.rsreu.tetris.game.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.rsreu.tetris.game.Coords;
import ru.rsreu.tetris.game.Figure;
import ru.rsreu.tetris.game.GameField;

public class CanvasGraphicsModule implements GraphicsModule {

    private static final int SIZE = 20;
    private Canvas canvas;

    public CanvasGraphicsModule(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void draw(GameField field) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for(int x = 0; x < GameField.COUNT_CELLS_X; x++){
            for(int y = 0; y < GameField.COUNT_CELLS_Y; y++){
                gc.setFill(field.getColor(x, y));
                gc.fillRect(x * SIZE, 400 - (y + 1) * SIZE, SIZE, SIZE);
            }
        }
        Figure figure = field.getFigure();
        gc.setFill(Color.YELLOW);
        for(Coords coords: figure.getCoords()){
            gc.fillRect(coords.getX() * SIZE, 400 - (coords.getY() + 1) * SIZE, SIZE, SIZE);
        }
    }
}
