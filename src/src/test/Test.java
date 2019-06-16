package src.test;

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
        try {
            String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\Joshua Lee\\Documents\\이현빈\\경기영재과학고등학교\\정보과학\\정보과학 프로젝트I\\IntelliJ-workspace\\SolveGarage\\src\\src\\test\\image.txt")));
            SecureHttpConnection.post(SecureHttpConnection.profileImgURL, content, serverPublicKey,
                    RSA.generateKeyPair());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
