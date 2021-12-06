package ru.rsreu.tetris.game;

import java.util.Arrays;

public class Game {
    public static final int MOVE_DOWNS_PER_SECOND = 3;
    public static final int FPS = 60;
    public static final int FRAMES_PER_MOVE = FPS / MOVE_DOWNS_PER_SECOND;
    public static final int BOOST_MULTIPLIER = 5;
    private GameGraphicsModule graphicsModule;
    private KeyboardHandleModule keyboardHandleModule;
    private boolean isEnd = false;
    private GameField gameField;
    private ShiftDirection shiftDirection = ShiftDirection.NONE;
    private boolean isRotate = false;
    private boolean isBoost = false;
    private int loopNumber = 0;

    public Game(){

    }

    public void start(){
        while(isEnd){
            input();
            logic();


        }
    }

    private void input(){

    }

    private void logic(){
        if(shiftDirection != ShiftDirection.NONE){
            gameField.tryShiftFigure(shiftDirection);
            shiftDirection = ShiftDirection.NONE;
        }
        if(isRotate){
            gameField.tryRotateFigure();
            isRotate = false;
        }
        if((loopNumber % (FRAMES_PER_MOVE / (isBoost? BOOST_MULTIPLIER: 1) ) == 0)){
            gameField.letFallDown();
        }
        this.isEnd = isEnd || gameField.isOverfilled();
    }
}
