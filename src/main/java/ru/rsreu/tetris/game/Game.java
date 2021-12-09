package ru.rsreu.tetris.game;

import javafx.scene.canvas.Canvas;
import ru.rsreu.tetris.game.graphics.CanvasGraphicsModule;
import ru.rsreu.tetris.game.graphics.GraphicsModule;
import ru.rsreu.tetris.game.input.KeyboardHandleModule;
import ru.rsreu.tetris.game.input.StageKeyboardHandleModule;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    public static final int MOVE_DOWNS_PER_SECOND = 3;
    public static final int FPS = 60;
    public static final int FRAMES_PER_MOVE = FPS / MOVE_DOWNS_PER_SECOND;
    public static final int BOOST_MULTIPLIER = 10;
    private final GraphicsModule graphicsModule;
    private KeyboardHandleModule keyboardHandleModule;
    private boolean isEnd = false;
    private final GameField gameField = new GameField();
    private ShiftDirection shiftDirection = ShiftDirection.NONE;
    private boolean isRotate = false;
    private boolean isBoost = false;
    private int loopNumber = 0;

    public Game(Canvas canvas) {
        graphicsModule = new CanvasGraphicsModule(canvas);
        keyboardHandleModule = new StageKeyboardHandleModule(canvas.getScene());
    }

    public void start() {
        graphicsModule.draw(this.gameField);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                input();
                logic();
                graphicsModule.draw(gameField);
                if(isEnd){
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, 0, 10);
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
        loopNumber = (loopNumber+1)%(FRAMES_PER_MOVE);
        this.isEnd = isEnd || gameField.isOverfilled();
    }
}
