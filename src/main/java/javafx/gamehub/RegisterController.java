package javafx.gamehub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private Label registerMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField gmailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button registerButton;
    public void registerButtonOnAction(ActionEvent actionEvent) {
        if ( !usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank() ) {
            registerMessageLabel.setText("You try to register!");
        } else {
            registerMessageLabel.setText("Please enter username, gmail and password!");
        }
    }
    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource("Login.fxml")).load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
