module ru.rsreu.tetris {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.rsreu.tetris to javafx.fxml;
    exports ru.rsreu.tetris;
}