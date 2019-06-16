package src.scene.main;

import com.google.gson.JsonObject;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.Utils.RSA;
import src.Utils.SecureHttpConnection;
import src.main.Main;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class UserDataController_logged_in implements Initializable {

    @FXML
    ImageView userImg, logoutBtn;
    @FXML
    Label userName;

    public void update() throws Exception {
        userName.setText(Main.id);

        JsonObject object = SecureHttpConnection.get(SecureHttpConnection.profileImgURL, "id=" + Main.id);
//        File outputfile = new File("../../../resources/caches/profile_"+Main.id+".png");
        boolean success = object.get("success").getAsBoolean();
        if (success) {
            String image = object.get("img").getAsString();

            BufferedImage bufferedImage = null;
            byte[] imageByte;

            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(image);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            bufferedImage = ImageIO.read(bis);
            bis.close();

//            ImageIO.write(bufferedImage, "png", outputfile);
            Image image_set = SwingFXUtils.toFXImage(bufferedImage, null);
            userImg.setImage(image_set);
        } else {
            // ..?
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        logoutBtn.setOnMouseClicked(e -> logout());
    }

    private void logout() {
        JsonObject object = null;
        try {
            object = SecureHttpConnection.post(SecureHttpConnection.logoutURL,
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
