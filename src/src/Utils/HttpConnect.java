package src.Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


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
     * @param id
     * @param password
     * @throws IOException
     */
    public void sendLoginPost(String id, String password) throws IOException {
        String url = "http://buttercrab.iptime.org:3080/login";
        URL object = new URL(url);
        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        String body = "{'id'='" + id + "','pw'='" + password + "'}";
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
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
    }
}
