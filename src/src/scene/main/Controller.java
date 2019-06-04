package src.scene.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import src.Utils.MultiScreen;
import src.main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for src.main Screen
 *
 */

public class Controller implements Initializable{
    @FXML Label loginLabel, solvegarage, discoverTab, categoriesTab, postTab, rankingTab;
    @FXML Pane leftBorder;
    @FXML Button signupBtn;
    @FXML TextField searchfield;
    @FXML ScrollPane contentPane;

    // TODO: make FXMLs files for each tab
    private String[] sceneName = {"discover", "categories", "post", "ranking"};
    private MultiScreen contentScreen = new MultiScreen(sceneName);
    private Parent discoverS, categoryS, postS, rankingS;
    // TODO: add screens to contentScreen

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try{
            rankingS = FXMLLoader.load(getClass().getResource("../../../FXMLs/rankingScene.fxml"));
        } catch(IOException e) {
            System.out.println("No file named \"../../../FXMLs/rankingScene.fxml\"");
        }
        contentScreen.addScreen("ranking", rankingS);
        contentPane.setContent(contentScreen.currentScreen());
        loginLabel.setOnMouseClicked(e -> Main.loginClick());
        solvegarage.setOnMouseClicked(e -> tabClick("discover"));
        discoverTab.setOnMouseClicked(e -> tabClick("discover"));
        categoriesTab.setOnMouseClicked(e -> tabClick("categories"));
        postTab.setOnMouseClicked(e -> tabClick("post"));
        rankingTab.setOnMouseClicked(e -> tabClick("ranking"));
    }

    private void tabClick(String sceneName) {
        System.out.println(sceneName);
        contentScreen.activate(sceneName);
        contentPane.setContent(contentScreen.currentScreen());
    }
}