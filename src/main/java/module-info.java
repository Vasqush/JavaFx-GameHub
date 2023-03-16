module javafx.gamehub {
    requires javafx.controls;
    requires javafx.fxml;

    opens javafx.gamehub to javafx.fxml;
    exports javafx.gamehub;

    opens javafx.gamehub.Tictactoe to javafx.fxml;
    exports javafx.gamehub.Tictactoe;
}