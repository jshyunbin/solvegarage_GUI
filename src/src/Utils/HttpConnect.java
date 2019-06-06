package src.Utils;

import src.main.Main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Base64;


/**
 * a utility class for connecting with server
 *
 * sendLoginPost() -> posts id and password to server and gets response codes
 *
 *
 */
public class HttpConnect {

    /**
     *
     * @param id the username of the account
     * @param password the password of the account
     * @return returns the JSON response content as String type
     * @throws IOException IOException on HttpURLConnection declaration
     */
    public String sendLoginPost(String id, String password, String type) throws IOException {
        String url = "http://buttercrab.iptime.org:3080/" + type;
        URL object = new URL(url);
        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        String body = "{'id'='" + id + "','pw'='" + password + "'}";
        KeyPair keyPair = RSA.generateKeyPair();

        String rsaBody = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()) + ":" + body + ":" +
                Base64.getEncoder().encodeToString(RSA.sign(body, keyPair.getPrivate().getEncoded()).getBytes());
        String rsaEncryptedBody = RSA.encrypt(rsaBody, Base64.getDecoder().decode(Main.getPublicKey()));
        Base64.getEncoder().encodeToString(rsaEncryptedBody.getBytes());

        DataOutputStream wr;
        try {
            wr = new DataOutputStream(con.getOutputStream());
        } catch(ConnectException e) {
            System.out.println("server connection time out");
            return "timed out";
        }
        wr.writeBytes(rsaEncryptedBody);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String decryptedResponse = RSA.decrypt(Base64.getDecoder().decode(response.toString()).toString(),
                keyPair.getPrivate().getEncoded());
        String[] response_with_signiture = decryptedResponse.split("[:]");

        for (int i = 0; i < 2; i++) {
            response_with_signiture[i] = Base64.getDecoder().decode(response_with_signiture[i]).toString();
        }

        if (!RSA.verify(response_with_signiture[0], response_with_signiture[1],
                Base64.getDecoder().decode(Main.getPublicKey()))) {
            return "fail";
        }

        //print result
        System.out.println(response_with_signiture[1]);
        return response_with_signiture[1];
    }

    public void getPublicKey() throws IOException {
        URL object = new URL("http://buttercrab.iptime.org:3080/get-key");
        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        Main.setPublicKey(response.toString());

        System.out.println(response.toString());
    }
}
