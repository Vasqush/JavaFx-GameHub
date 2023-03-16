package javafx.gamehub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    @FXML
    public void game1(MouseEvent mouseEvent) {
        bp.setCenter(ap);
    }
    @FXML
    public void game2(MouseEvent mouseEvent) {
        loadPage("Game2");
    }
    @FXML
    public void game3(MouseEvent mouseEvent) {
        loadPage("Game3");
    }
    @FXML
    public void game4(MouseEvent mouseEvent) {
        loadPage("Game4");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void loadPage(String page){
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bp.setCenter(root);
    }
}
