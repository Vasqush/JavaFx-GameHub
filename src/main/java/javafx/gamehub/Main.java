package javafx.gamehub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("Signup.fxml"));
            Scene scene = new Scene(root.load());
            stage.setScene(scene);
            stage.setTitle("GameHub");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}