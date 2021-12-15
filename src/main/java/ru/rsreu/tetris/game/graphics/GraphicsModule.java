package ru.rsreu.tetris.game.graphics;

import ru.rsreu.tetris.game.GameField;

public interface GraphicsModule {
    void drawGame(GameField field);

    void drawEndPanel(int score);
}
