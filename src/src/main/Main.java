package src.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private String currentScreen;
    private Stage loginStage;
    private static Parent loginpage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        loginpage = FXMLLoader.load(getClass().getResource("../../FXMLs/loginPage.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../../FXMLs/mainScreen.fxml"));
        primaryStage.setTitle("solvegarage");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
        currentScreen = "Main";
    }


    public static void main(String[] args) {
        launch(args);
    }


    /**
     *  static method activated when login button is clicked
     *  TODO: add Runnable class that handles login transfer
     *
     */
    public static void loginClick() {
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(loginpage));
        stage.setResizable(false);
        stage.show();
        System.out.println("loginPage");
    }
}
