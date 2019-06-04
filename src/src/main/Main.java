package src.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.scene.login.LoginTransfer;

import java.io.IOException;

public class Main extends Application {

    private Stage loginStage;
    private static Parent loginpage;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        loginpage = FXMLLoader.load(getClass().getResource("../../FXMLs/loginPage.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../../FXMLs/mainScreen.fxml"));
        loginpage = FXMLLoader.load(getClass().getResource("../../FXMLs/loginPage.fxml"));
        primaryStage.setTitle("solvegarage");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    /**
     *  static method activated when login button is clicked
     *  TODO: add Thread class that handles login transfer
     *
     */
    public static void loginClick() {
        Thread loginTransfer = new LoginTransfer();
        stage = new Stage();
        stage.setTitle("login");
        stage.setResizable(false);
        stage.setScene(new Scene(loginpage));
        stage.show();
        loginTransfer.start();
        synchronized (loginTransfer) {
            try {
                loginTransfer.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
