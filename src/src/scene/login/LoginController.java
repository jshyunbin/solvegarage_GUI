package src.scene.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import src.Utils.HttpConnect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *  Controller for login Screen
 *
 *
 */

public class LoginController implements Initializable {

    @FXML TextField usernameText;
    @FXML PasswordField passwordText;
    @FXML Button loginBtn;
    @FXML Label signupText;

    String id, password;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        signupText.setOnMouseClicked(e -> signup());
        loginBtn.setOnMouseClicked(e -> login());
    }

    /**
     *  Activates when signup text is clicked.
     *  changes src.scene to signup src.scene
     */
    private void signup() {

    }

    private void login() {
        id = usernameText.getText();
        password = passwordText.getText();
        usernameText.clear();
        passwordText.clear();
        HttpConnect httpConnect = new HttpConnect();
        try {
            httpConnect.sendLoginPost(id, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
