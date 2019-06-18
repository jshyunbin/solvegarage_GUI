package src.test;

import com.google.gson.JsonObject;
import src.Utils.RSA;
import src.Utils.SecureHttpConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Base64;
import java.util.Objects;

public class Test {
    public static void main(String[] args) throws IOException {
        byte[] serverPublicKey = null;
        try {
            serverPublicKey = SecureHttpConnection.getServerPublicKey();
        } catch (ConnectException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        String id = "test";
        String password = "test";
        String token="";
        JsonObject object = null;
        try {
            object = SecureHttpConnection.post(SecureHttpConnection.loginURL, "{'id':'" + id + "','pw':'" + password +
                    "'}", serverPublicKey, RSA.generateKeyPair());
        } catch (Exception e) {
            System.out.println("login failed...\ncheck server");
            return;
        }

        boolean success = object.get("success").getAsBoolean();


        if (success) {
            token = object.get("token").getAsString();
        }else {
            int code = object.get("code").getAsInt();
            if (code == 1) {
                System.out.println("username is wrong");
            } else if (code == 2) {
                System.out.println("password is wrong");
            }
        }
        JsonObject root = null;
        File file = new File("C:\\Users\\Joshua Lee\\Documents\\이현빈\\경기영재과학고등학교\\정보과학\\정보과학 " +
                "프로젝트I\\IntelliJ-workspace\\SolveGarage\\src\\src\\test\\test01.jpg");
        FileInputStream in = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        //noinspection ResultOfMethodCallIgnored
        in.read(data);
        String img = Base64.getEncoder().encodeToString(data);
        System.out.println(img);
        try {
            root = SecureHttpConnection.post("http://buttercrab.iptime.org:3080/profile-image", "{'id':'test','token':'" + token + "','img':'" + img + "'}", serverPublicKey, Objects.requireNonNull(RSA.generateKeyPair()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert root != null;
        token = root.get("token").getAsString();
        assert root.get("success").getAsBoolean();
    }
}
