package src.scene.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import src.Utils.MultiScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for src.main Screen
 *
 */

public class Controller implements Initializable{

    @FXML
    static Pane leftBorder, userData;
    private static Parent user_defaultS, user_logged_inS;
    @FXML TextField searchfield;
    @FXML ScrollPane contentPane;

    private String[] sceneName = {"discover", "categories", "post", "ranking"};
    private MultiScreen contentScreen = new MultiScreen(sceneName);
    private Parent discoverS, categoryS, postS, rankingS;
    @FXML
    Label solvegarage, discoverTab, categoriesTab, postTab, rankingTab;

    /**
     *
     */
    public static void updateUserData() {
        userData.getChildren().removeAll();
        userData.getChildren().add(user_logged_inS);
    }


    private void tabClick(String sceneName) {
        System.out.println(sceneName);
        contentScreen.activate(sceneName);
        contentPane.setContent(contentScreen.currentScreen());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try{
            rankingS = FXMLLoader.load(getClass().getResource("../../../FXMLs/rankingScene.fxml"));
            user_defaultS = FXMLLoader.load(getClass().getResource("../../../FXMLs/userData_default.fxml"));
            user_logged_inS = FXMLLoader.load(getClass().getResource("../../../FXMLs/userData_logged_in.fxml"));
        } catch(IOException e) {
            System.out.println("No file named \"../../../FXMLs/rankingScene.fxml\"");
        }
        userData = new Pane();
        userData.getChildren().add(user_defaultS);
        contentScreen.addScreen("ranking", rankingS);
        contentPane.setContent(contentScreen.currentScreen());
        solvegarage.setOnMouseClicked(e -> tabClick("discover"));
        discoverTab.setOnMouseClicked(e -> tabClick("discover"));
        categoriesTab.setOnMouseClicked(e -> tabClick("categories"));
        postTab.setOnMouseClicked(e -> tabClick("post"));
        rankingTab.setOnMouseClicked(e -> tabClick("ranking"));

    }
}
