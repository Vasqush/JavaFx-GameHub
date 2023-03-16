package javafx.gamehub;

import javafx.gamehub.pingpong.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage {
@FXML
public void BackBTN(ActionEvent event) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource("Login.fxml")).load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toPingpong(ActionEvent event) throws Exception {
    Pingpong pong = new Pingpong();
        pong.start(new Stage());
    }
}

