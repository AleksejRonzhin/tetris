package ru.rsreu.tetris.game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import ru.rsreu.tetris.game.graphics.CanvasGraphicsModule;
import ru.rsreu.tetris.game.graphics.GraphicsModule;
import ru.rsreu.tetris.game.input.KeyboardHandleModule;
import ru.rsreu.tetris.game.input.SceneKeyboardHandleModule;

public class Game {
    public static final int MOVE_DOWNS_PER_SECOND = 3;
    public static final int FPS = 60;
    public static final int FRAMES_PER_MOVE = FPS / MOVE_DOWNS_PER_SECOND;
    public static final int BOOST_MULTIPLIER = 15;
    private final GraphicsModule graphicsModule;
    private final KeyboardHandleModule keyboardHandleModule;
    private boolean isEnd = false;
    private boolean isRotate = false;
    private boolean isBoost = false;
    private ShiftDirection shiftDirection = ShiftDirection.NONE;
    private int loopNumber = 0;
    private int score = 0;
    private final GameField gameField = new GameField();
    private final AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            input();
            logic();
            graphicsModule.drawGame(gameField);
            if(isEnd){
                end();
            }
        }
    };

    public Game(Canvas canvas) {
        graphicsModule = new CanvasGraphicsModule(canvas);
        keyboardHandleModule = new SceneKeyboardHandleModule(canvas.getScene());
    }

    public void start() {
        this.animationTimer.start();
    }

    public void stop() {
        this.animationTimer.stop();
    }

    public void end(){
        stop();
        int score = this.gameField.getScore();
        this.graphicsModule.drawEndPanel(score);
    }

    private void input() {
        keyboardHandleModule.update();
        this.shiftDirection = keyboardHandleModule.getShiftDirection();
        this.isEnd = keyboardHandleModule.wasEscPressed();
        this.isRotate = keyboardHandleModule.wasRotateRequested();
        this.isBoost = keyboardHandleModule.wasBoostRequested();
        keyboardHandleModule.clean();
    }

    private void logic() {
        if (shiftDirection != ShiftDirection.NONE) {
            gameField.tryShiftFigure(shiftDirection);
            shiftDirection = ShiftDirection.NONE;
        }
        if (isRotate) {
            gameField.tryRotateFigure();
            isRotate = false;
        }
        if ((loopNumber % (FRAMES_PER_MOVE / (isBoost ? BOOST_MULTIPLIER : 1)) == 0)) {
            gameField.letFallDown();
        }
        loopNumber = (loopNumber+1)%FRAMES_PER_MOVE;
        this.isEnd = isEnd || gameField.isOverfilled();
    }
}
