package javafx.gamehub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

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
    public void registerButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Window owner = registerButton.getScene().getWindow();

        System.out.println(usernameTextField.getText());
        System.out.println(gmailTextField.getText());
        System.out.println(passwordTextField.getText());
        if (usernameTextField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter username!");
            return;
        }

        if (gmailTextField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your gmail!");
            return;
        }
        if (passwordTextField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password!");
            return;
        }

        String username = usernameTextField.getText();
        String gmail = gmailTextField.getText();
        String password = passwordTextField.getText();

        DatabaseConnection database = new DatabaseConnection();
        database.insertRecord(username, gmail, password);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + usernameTextField.getText());
        switchToLogin(actionEvent);
    }
    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource("Login.fxml")).load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}