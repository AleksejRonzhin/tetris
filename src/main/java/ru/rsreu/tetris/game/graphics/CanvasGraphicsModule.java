package ru.rsreu.tetris.game.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.rsreu.tetris.game.ColorBundle;
import ru.rsreu.tetris.game.Coords;
import ru.rsreu.tetris.game.Figure;
import ru.rsreu.tetris.game.GameField;

public class CanvasGraphicsModule implements GraphicsModule {

    private final int size;
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final ColorBundle colorBundle;
    private final int borderSize = 5;

    public CanvasGraphicsModule(Canvas canvas, ColorBundle colorBundle) {
        this.canvas = canvas;
        this.colorBundle = colorBundle;
        gc = canvas.getGraphicsContext2D();
        size = (int) ((canvas.getHeight() - 2 * borderSize) / GameField.COUNT_CELLS_Y);
    }

    @Override
    public void drawGame(GameField field) {
        drawBackground();
        drawField(field);
        drawFigure(field.getFigure());
    }

    private void drawBackground() {
        gc.setFill(this.colorBundle.getBackgroundColor());
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(this.colorBundle.getTextColor());
        gc.setLineWidth(5);
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawField(GameField field) {
        for (int x = 0; x < GameField.COUNT_CELLS_X; x++) {
            for (int y = 0; y < GameField.COUNT_CELLS_Y; y++) {
                drawBlock(field.getBlock(x, y).getColor(), new Coords(x, y));
            }
        }
    }

    private void drawBlock(Color color, Coords coords) {
        gc.setFill(color);
        gc.fillRect( borderSize + coords.getX() * size + 1.5,
                 canvas.getHeight() - borderSize - (coords.getY() + 1) * size + 1.5, size - 3, size - 3);
    }

    private void drawFigure(Figure figure) {
        for (Coords coords : figure.getCoords()) {
            drawBlock(figure.getColor(), coords);
        }
    }


    @Override
    public void drawEndPanel(GameField field) {
        drawBackground();
        for (int x = 0; x < GameField.COUNT_CELLS_X; x++) {
            for (int y = 0; y < GameField.COUNT_CELLS_Y; y++) {
                if (field.isNotEmpty(x, y)) {
                    drawBlock(this.colorBundle.getTextColor(), new Coords(x, y));
                }
            }
        }
    }
}
