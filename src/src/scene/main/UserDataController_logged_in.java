package src.scene.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import src.main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDataController_logged_in implements Initializable {

    @FXML
    ImageView userImg;
    @FXML
    Label userName;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        userName.setText(Main.id);

    }
}
