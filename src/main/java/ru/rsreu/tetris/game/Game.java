package ru.rsreu.tetris.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import ru.rsreu.tetris.game.figure.ShiftDirection;
import ru.rsreu.tetris.game.graphics.CanvasGraphicsModule;
import ru.rsreu.tetris.game.graphics.GraphicsModule;
import ru.rsreu.tetris.game.input.KeyboardHandleModule;
import ru.rsreu.tetris.game.input.SceneKeyboardHandleModule;

import java.util.ResourceBundle;

public class Game extends Node {
    public static final int MOVE_DOWNS_PER_SECOND = 3;
    public static final int FPS = 60;
    public static final int FRAMES_PER_MOVE = FPS / MOVE_DOWNS_PER_SECOND;
    public static final int BOOST_MULTIPLIER = 15;
    private final GraphicsModule graphicsModule;
    private final KeyboardHandleModule keyboardHandleModule;
    private final GameField gameField;
    private final Scene scene;
    private final ResourceBundle bundle;
    private boolean isEnd = false;
    private boolean isRotate = false;
    private boolean isBoost = false;
    private boolean isStash = false;
    private ShiftDirection shiftDirection = ShiftDirection.NONE;
    private int loopNumber = 0;
    private final AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            input();
            logic();
            graphicsModule.drawGame(gameField);
            if (isEnd) {
                end();
            }
        }
    };

    public Game(Canvas canvas, Canvas nextFigure, Canvas stashFigure, ResourceBundle bundle, ColorBundle colorBundle) {
        graphicsModule = new CanvasGraphicsModule(canvas, nextFigure, stashFigure, colorBundle);
        this.scene = canvas.getScene();
        keyboardHandleModule = new SceneKeyboardHandleModule(this.scene);
        this.bundle = bundle;
        gameField = new GameField(colorBundle);
    }

    public void start() {
        this.animationTimer.start();
    }

    public void stop() {
        this.animationTimer.stop();
    }

    public void end() {
        stop();
        this.graphicsModule.drawEndPanel(gameField);
        scene.lookup("#btnStart").setDisable(false);
        scene.lookup("#btnStop").setVisible(false);
        scene.lookup("#btnContinue").setVisible(false);
    }

    private void input() {
        keyboardHandleModule.update();
        this.shiftDirection = keyboardHandleModule.getShiftDirection();
        this.isEnd = keyboardHandleModule.wasEscPressed();
        this.isRotate = keyboardHandleModule.wasRotateRequested();
        this.isBoost = keyboardHandleModule.wasBoostRequested();
        this.isStash = keyboardHandleModule.wasStashRequested();
        keyboardHandleModule.clean();
    }

    private void logic() {
        if (isStash) {
            gameField.stashFigure();
            isStash = false;
        }
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
        loopNumber = (loopNumber + 1) % FRAMES_PER_MOVE;
        this.isEnd = isEnd || gameField.isOverfilled();
        setScore();
    }

    private void setScore() {
        Label label = (Label) scene.lookup("#lblScore");
        label.setText(bundle.getString("score") + ": " + this.gameField.getScore());
    }



}
