package ru.rsreu.tetris.game.graphics;

import ru.rsreu.tetris.game.Figure;
import ru.rsreu.tetris.game.GameField;

public interface GraphicsModule {
    void drawGame(GameField field);

    void drawNextFigure(Figure figure);

    void drawStashFigure(Figure figure);

    void drawEndPanel(GameField field);
}
