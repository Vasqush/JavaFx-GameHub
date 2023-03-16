package javafx.gamehub;

import javafx.event.ActionEvent;
import javafx.gamehub.Pingpong.Pingpong;
import javafx.stage.Stage;

public class HomePage {
    public void toPingpong(ActionEvent event) throws Exception {
    Pingpong pong = new Pingpong();
        pong.start(new Stage());
    }
}

