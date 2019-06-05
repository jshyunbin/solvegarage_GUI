package src.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * the Main class takes care of the Stages
 *
 * loginStage -> the stage that is used for login screen
 * registerStage -> the stage that is used for sign up screen
 * token -> current user's token
 */
public class Main extends Application {

    private static Stage loginStage, registerStage;
    private static String token;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // loading fxml files
        Parent root = FXMLLoader.load(getClass().getResource("../../FXMLs/mainScreen.fxml"));
        Parent loginpage = FXMLLoader.load(getClass().getResource("../../FXMLs/loginPage.fxml"));
        Parent registerpage = FXMLLoader.load(getClass().getResource("../../FXMLs/registerPage.fxml"));

        primaryStage.setTitle("solvegarage");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();

        loginStage = new Stage();
        loginStage.setTitle("login");
        loginStage.setResizable(false);
        loginStage.setScene(new Scene(loginpage));

        registerStage = new Stage();
        registerStage.setTitle("sign up");
        registerStage.setResizable(false);
        registerStage.setScene(new Scene(registerpage));

        token = null;
    }


    public static void main(String[] args) {
        launch(args);
    }


    /**
     *  static method activated when login button is clicked
     *
     */
    public synchronized static void loginClick() {
        loginStage.show();
    }

    public static void closeStage(String stageName) {
        if(stageName.equals("login")) {
            loginStage.close();
        }
        else if (stageName.equals("sign up")) {
            registerStage.close();
        }
    }

    public static void setToken(String token1) {
        token = token1;
    }
}
