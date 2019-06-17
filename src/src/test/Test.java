package src.test;

import com.google.gson.JsonObject;
import src.Utils.RSA;
import src.Utils.SecureHttpConnection;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) {
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
            }
            else if (code == 2) {
                System.out.println("password is wrong");
            }
        }
        try {
            String content = new String(Files.readAllBytes(Paths.get("src\\src\\test\\image.txt")));
            System.out.println(content);
            System.out.println(content.length());
            SecureHttpConnection.post(SecureHttpConnection.profileImgURL, "{'id':'test','token':"+token+","+content,
                    serverPublicKey, RSA.generateKeyPair());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
