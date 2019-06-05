package src.Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.ConnectException;


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
    public String sendPost(String id, String password, String type) throws IOException {
        String url = "http://buttercrab.iptime.org:3080/" + type;
        URL object = new URL(url);
        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        String body = "{'id'='" + id + "','pw'='" + password + "'}";
        DataOutputStream wr;
        try {
            wr = new DataOutputStream(con.getOutputStream());
        } catch(ConnectException e) {
            e.printStackTrace();
            System.out.println("server connection timed out");
            return "timed out";
        }
        wr.writeBytes(body);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        return response.toString();
    }
}
