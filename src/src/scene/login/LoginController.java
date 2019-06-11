package src.scene.login;


import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import src.Utils.RSA;
import src.Utils.SecureHttpConnection;
import src.main.Main;
import src.scene.main.Controller;

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


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        signupText.setOnMouseClicked(e -> signup());
        loginBtn.setOnMouseClicked(e -> login());
        signupText.setOnMouseClicked(e -> Main.openRegister());
    }

    /**
     *  Activates when signup text is clicked.
     *  changes src.scene to signup src.scene
     */
    private void signup() {

    }

    private void login() {
        String id = usernameText.getText();
        String password = passwordText.getText();
        JsonObject object = null;
        try {
            object = SecureHttpConnection.post(SecureHttpConnection.loginURL, "{'id':'" + id + "','pw':'" + password +
                    "'}", Main.getServerPublicKey(), RSA.generateKeyPair());
        } catch (Exception e) {
            Main.errorScreen();
            System.out.println("login failed...\ncheck server");
        }

        boolean success = object.get("success").getAsBoolean();


        if (success) {
            usernameText.clear();
            passwordText.clear();
            Main.closeStage();
            Main.setToken(object.get("token").getAsString());
            Main.id = id;

            Controller controller = Main.root.getController();
            controller.updateUserData();
        }else {
            int code = object.get("code").getAsInt();
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
