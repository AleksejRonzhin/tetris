module ru.rsreu.tetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ru.rsreu.tetris to javafx.fxml;
    exports ru.rsreu.tetris;
}