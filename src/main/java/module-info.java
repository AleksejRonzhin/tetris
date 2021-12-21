module ru.rsreu.tetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens ru.rsreu.tetris to javafx.fxml;
    exports ru.rsreu.tetris;
}