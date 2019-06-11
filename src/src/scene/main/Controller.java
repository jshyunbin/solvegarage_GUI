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

    private static Parent user_defaultS, user_logged_inS;
    @FXML TextField searchfield;
    @FXML ScrollPane contentPane;
    @FXML
    Pane leftBorder, userData;
    @FXML
    Label solvegarage, discoverTab, categoriesTab, postTab, rankingTab;
    private String[] sceneName = {"discover", "categories", "post", "ranking"};
    private MultiScreen contentScreen = new MultiScreen(sceneName);
    private String[] loginWrapName = {"default", "logged_in"};
    private MultiScreen loginWrap = new MultiScreen(loginWrapName);
    private Parent discoverS, categoryS, postS, rankingS;

    /**
     *
     */
    public void updateUserData() {
        userData.getChildren().removeAll();
        loginWrap.activate("logged_in");
        userData.getChildren().add(loginWrap.currentScreen());
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

        contentScreen.addScreen("ranking", rankingS);
        contentPane.setContent(contentScreen.currentScreen());

        loginWrap.addScreen("default", user_defaultS);
        loginWrap.addScreen("logged_in", user_logged_inS);
        loginWrap.activate("default");

        userData.getChildren().add(loginWrap.currentScreen());

        solvegarage.setOnMouseClicked(e -> tabClick("discover"));
        discoverTab.setOnMouseClicked(e -> tabClick("discover"));
        categoriesTab.setOnMouseClicked(e -> tabClick("categories"));
        postTab.setOnMouseClicked(e -> tabClick("post"));
        rankingTab.setOnMouseClicked(e -> tabClick("ranking"));

    }
}
