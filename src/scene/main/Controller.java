package scene.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for main Screen
 *
 */

public class Controller implements Initializable{
    @FXML Label loginLabel, solvegarage;
    @FXML Pane leftBorder;
    @FXML Button signupBtn;
    @FXML TextField searchfield;
    @FXML ScrollPane contentPane;

    // TODO: make FXMLs files for each tab
    MultiScreen contentScreen = new MultiScreen();
    private Parent leftTab;
    private Parent loginpage;
    String currentPage = "discover";
    // TODO: add screens to contentScreen

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try{
            leftTab = FXMLLoader.load(getClass().getResource("../../FXMLs/leftTab.fxml"));
            loginpage = FXMLLoader.load(getClass().getResource("../../FXMLs/loginPage.fxml"));
        } catch(IOException e) {
            System.out.println("No file named \"../../FXMLs/leftTab.fxml\" or \"../../FXMLs/loginPage.fxml\"");
        }
        leftBorder.getChildren().add(leftTab);
        contentPane.setContent(contentScreen.currentScreen());
        loginLabel.setOnMouseClicked(e -> loginClick());
        solvegarage.setOnMouseClicked(e -> iconClick());
    }

    private void loginClick() {
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(loginpage));
        stage.setResizable(false);
        stage.show();
        System.out.println("loginPage");
    }

    private void iconClick() {
        currentPage = "discover";
        System.out.println("discover");
    }


}
