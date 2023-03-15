package javafx.gamehub;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class LoginController {

    @FXML
    Button btnScene1 , btnScene2;

    public void handleBtn1() throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));

    Stage window = (Stage) btnScene1.getScene().getWindow();
    window.setScene(new Scene(root, 750, 500));
    }
    public void handleBtn2() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Stage window = (Stage) btnScene2.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }
}
































































