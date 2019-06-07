package src.scene.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import src.main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDataController_default implements Initializable {

    @FXML
    Label loginLabel;
    @FXML
    Button signupBtn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loginLabel.setOnMouseClicked(e -> Main.openLogin());
        signupBtn.setOnMouseClicked(e -> Main.openRegister());
    }
}
