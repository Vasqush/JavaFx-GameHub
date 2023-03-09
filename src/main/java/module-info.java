module javafx.gamehub {
    requires javafx.controls;
    requires javafx.fxml;


    opens javafx.gamehub to javafx.fxml;
    exports javafx.gamehub.pingpong;
}