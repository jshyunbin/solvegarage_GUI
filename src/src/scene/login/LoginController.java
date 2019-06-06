package src.scene.login;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import src.Utils.HttpConnect;
import src.main.Main;

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

    private String id, password;

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
        HttpConnect httpConnect = new HttpConnect();
        String responseCode = "";
        try {
            responseCode = httpConnect.sendLoginPost(id, password, "login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responseCode.equals("timed out")) {
            Main.closeStage("login");
            return;
        }
        JsonParser parser = new JsonParser();
        JsonObject root = parser.parse(responseCode).getAsJsonObject();
        boolean success = root.get("success").getAsBoolean();
        if (success) {
            usernameText.clear();
            passwordText.clear();
            Main.closeStage("login");
            Main.setToken(root.get("token").getAsString());
        }else {
            int code = root.get("code").getAsInt();
            if (code == 1) {
                usernameText.clear();
                passwordText.clear();
                System.out.println("username is wrong");
            }
            else if (code == 2) {
                passwordText.clear();
                System.out.println("password is wrong");
            }
        }
    }
}
