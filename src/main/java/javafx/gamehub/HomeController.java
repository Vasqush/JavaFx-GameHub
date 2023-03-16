package javafx.gamehub;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.gamehub.Tetris.Tetris;
import javafx.gamehub.pingpong.Pingpong;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    Tetris tetris = new Tetris();
    Pingpong pingpong = new Pingpong();

    public void switchToTictactoe(ActionEvent event) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource("Tictactoe.fxml")).load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTetris() {
        tetris.start(new Stage());
    }
    public void switchToPingPong() throws Exception {
        pingpong.start(new Stage());
    }
    public void BackToLogin(ActionEvent event) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource("Login.fxml")).load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}