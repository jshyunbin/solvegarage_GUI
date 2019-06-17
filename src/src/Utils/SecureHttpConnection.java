package src.Utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyPair;
import java.util.Base64;

/**
 * A public class used for sending Http requests such as POST or GET.
 * Supports several
 */
public class SecureHttpConnection {

    public static String deleteAccountURL = "http://buttercrab.iptime.org:3080/delete-account", registerAccountURL =
            "http://buttercrab.iptime.org:3080/register", loginURL = "http://buttercrab.iptime.org:3080/login",
            logoutURL = "http://buttercrab.iptime.org:3080/logout", problemsURL =
            "http://buttercrab.iptime.org:3080/problem/", profileImgURL = "http://buttercrab.iptime.org:3080/profile-image";


    /**
     * A public static method for getting server public key
     * <p>
     * Gets the server public key by sending GET request to the server
     *
     * @return returns the server public key as byte arrays
     * @throws IOException exception on con.getInputStream() method. Occurs when server is down.
     */
    public static byte[] getServerPublicKey() throws IOException {
        URL url = new URL("http://buttercrab.iptime.org:3080/get-key");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        return Base64.getDecoder().decode(br.readLine());
    }

    /**
     * a public static method for POST HTTP requests
     *
     * @param url             the url you want to send POST
     * @param data            the data you want to send to the desired url
     * @param serverPublicKey the server public key
     * @param clientKey       the client key
     * @return returns the POST request result as a JsonObject
     * @throws Exception exception occurs when server is down
     */
    public static JsonObject post(String url, String data, byte[] serverPublicKey, KeyPair clientKey) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setDoOutput(true);

        data = Base64.getEncoder().encodeToString(clientKey.getPublic().getEncoded()) + ":"
                + Base64.getEncoder().encodeToString(data.getBytes()) + ":"
                + RSA.sign(data, clientKey.getPrivate().getEncoded());

        byte[] aesKey = AES.generateKey();
        data = AES.encrypt(data, aesKey);
        if (data == null) return null;
        data = RSA.encrypt(new String(aesKey), serverPublicKey) + ":" + data;

        DataOutputStream os = new DataOutputStream(con.getOutputStream());
        os.writeBytes(data);
        os.flush();
        os.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String[] input = br.readLine().split("[:]");
        String key = RSA.decrypt(input[0], clientKey.getPrivate().getEncoded());
        if (key == null) return null;
        String body = AES.decrypt(input[1], key.getBytes());
        if (body == null) return null;

        String[] content = body.split("[:]");
        String json = new String(Base64.getDecoder().decode(content[0]));
        String sign = content[1];

        if (!RSA.verify(json, sign, serverPublicKey)) return null;
        JsonParser parser = new JsonParser();
        return parser.parse(json).getAsJsonObject();
    }

    /**
     * A public static method for GET HTTP requests
     *
     * @param url       the url you want to GET from
     * @param parameter the parameters you enter to send GET requests
     * @return returns the GET result as a JsonObject
     * @throws Exception exception on HttpURLConnection openConnection method => Exception occurs when server is down
     */
    public static JsonObject get(String url, String parameter) throws Exception {
        URL obj = new URL(url + parameter);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        JsonParser parser = new JsonParser();
        return parser.parse(br.readLine()).getAsJsonObject();
    }
}