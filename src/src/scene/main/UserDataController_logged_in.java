package src.scene.main;

import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import src.Utils.RSA;
import src.Utils.SecureHttpConnection;
import src.main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDataController_logged_in implements Initializable {

    @FXML
    ImageView userImg, logoutBtn;
    @FXML
    Label userName;

    public void update() {
        userName.setText(Main.id);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        logoutBtn.setOnMouseClicked(e -> logout());
    }

    private void logout() {
        JsonObject object = null;
        try {
            object = SecureHttpConnection.post(SecureHttpConnection.loginURL,
                    "{'id':'" + Main.id + "','token':'" + Main.getToken() + "'}", Main.getServerPublicKey(),
                    RSA.generateKeyPair());
        } catch (Exception e) {
            Main.errorScreen();
            System.out.println("logout failed...\ncheck server");
            return;
        }

        boolean success = object.get("success").getAsBoolean();
        if (success) {
            Main.id = null;
            Main.setToken(null);
            Controller controller = Main.root.getController();
            controller.updateUserData("default");
        } else {

        }
    }
}
