package src.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.Utils.SecureHttpConnection;

import java.net.ConnectException;

/**
 * the Main class takes care of the Stages
 *
 * loginStage -> the stage that is used for login screen
 * registerStage -> the stage that is used for sign up screen
 * token -> current user's token
 */
public class Main extends Application {

    public static String id;
    private static String token;
    private static Stage loginStage, errorStage;
    private static byte[] serverPublicKey;
    private static Scene loginScene, registerScene, errorScene;
    public static FXMLLoader root;


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method of the Main class.
     *
     * @param primaryStage primary stage
     * @throws Exception exception on loading fxml files
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // loading fxml files
        root = new FXMLLoader(getClass().getResource("../../FXMLs/mainScreen.fxml"));
        Parent loginPage = FXMLLoader.load(getClass().getResource("../../FXMLs/loginPage.fxml"));
        Parent registerPage = FXMLLoader.load(getClass().getResource("../../FXMLs/registerPage.fxml"));
        Parent errorPage = FXMLLoader.load(getClass().getResource("../../FXMLs/errorScreen.fxml"));

        primaryStage.setTitle("solvegarage");
        primaryStage.setScene(new Scene(root.load(), 1000, 800));
        primaryStage.setResizable(false);
        primaryStage.show();

        loginScene = new Scene(loginPage);
        registerScene = new Scene(registerPage);
        errorScene = new Scene(errorPage);

        loginStage = new Stage();
        loginStage.setResizable(false);

        errorStage = new Stage();
        errorStage.setResizable(false);
        errorStage.setScene(errorScene);

        token = null;
        try {
            serverPublicKey = SecureHttpConnection.getServerPublicKey();
        } catch (ConnectException e) {
            errorScreen();
        }
    }


    /**
     * sets token value
     *
     * @param token1 the token value you desire to set
     */
    public static void setToken(String token1) {
        token = token1;
    }

    public static String getToken() {
        return token;
    }




    /**
     * static method activated when login button is clicked
     */
    public static void openLogin() {
        loginStage.setTitle("login");
        loginStage.setScene(loginScene);
        loginStage.show();
    }

    /**
     * static method activated when register text is clicked inside the login stage.
     */
    public static void openRegister() {
        loginStage.setScene(registerScene);
        loginStage.setTitle("sign up");
        loginStage.show();
    }

    /**
     * closes the current
     */
    public static void closeStage() {
        loginStage.close();
//        Controller.updateUserData();
    }

    public static void errorScreen() {
        errorStage.show();
    }


    //Tools for handling token and public key value

    /**
     * gets the server public key
     *
     * @return returns the server public key
     */
    public static byte[] getServerPublicKey() {
        return serverPublicKey;
    }

    /**
     * sets public key value
     *
     * @param serverPublicKey1 the public key value you desire to set
     */
    public static void setPublicKey(byte[] serverPublicKey1) {
        serverPublicKey = serverPublicKey1;
    }

}
