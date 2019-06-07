package src.scene.login;

import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import src.Utils.RSA;
import src.Utils.SecureHttpConnection;
import src.main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    TextField idField;
    @FXML
    PasswordField pwField;
    @FXML
    Button signupBtn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        signupBtn.setOnMouseClicked(e -> register());
    }

    private void register() {
        String id = idField.getText();
        String password = pwField.getText();
        JsonObject object = null;
        try {
            object = SecureHttpConnection.post(SecureHttpConnection.registerAccountURL, "{'id':'" + id + "','pw':'" + password +
                    "'}", Main.getServerPublicKey(), RSA.generateKeyPair());
        } catch (Exception e) {
            System.out.println("sign up failed...\ncheck server");
        }

        boolean success = object.get("success").getAsBoolean();
        if (success) {
            Main.setToken(object.get("token").getAsString());
            Main.closeStage();
        } else {
            System.out.println("failed on register");
        }
    }
}
