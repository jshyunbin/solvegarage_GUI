package src.scene.main;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import src.Utils.MultiScreen;
import src.scene.home.HomeController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for src.main Screen
 *
 */

public class Controller implements Initializable{

    private FXMLLoader user_logged_inS, user_defaultS;
    @FXML TextField searchfield;
    @FXML ScrollPane contentPane;
    @FXML
    Pane leftBorder, userData;
    @FXML
    Label solvegarage, homeTab, categoriesTab, postTab, rankingTab;
    private String[] sceneName = {"home", "categories", "post", "ranking"};
    private MultiScreen contentScreen = new MultiScreen(sceneName);
    private String[] loginWrapName = {"default", "logged_in"};
    private MultiScreen loginWrap = new MultiScreen(loginWrapName);
    private FXMLLoader homeS, categoryS, postS, rankingS;

    /**
     * Updates user data (such as username and user profile image).
     * Runs when login of register process is finished.
     *
     */
    public void updateUserData(String type) {
        userData.getChildren().clear();
        if (type.equals("logged_in")) {
            loginWrap.activate("logged_in");
            UserDataController_logged_in controller_logged_in = user_logged_inS.getController();
            try {
                controller_logged_in.update();
            } catch (Exception e) {
                System.out.println("Error on server");
            }
            userData.getChildren().add(loginWrap.currentScreen());
        }
        else if (type.equals("default")) {
            loginWrap.activate("default");
            userData.getChildren().add(loginWrap.currentScreen());
        }
    }

    /**
     * Runs when tab is clicked. Switches the scene to the desired scene.
     *
     * @param sceneName the scene name clicked
     */
    private void tabClick(String sceneName) {
        System.out.println(sceneName);
        contentScreen.activate(sceneName);
        contentPane.setContent(contentScreen.currentScreen());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        homeS = new FXMLLoader(getClass().getResource("../../../FXMLs/homeScene.fxml"));
        postS = new FXMLLoader(getClass().getResource("../../../FXMLs/postScene.fxml"));
        rankingS = new FXMLLoader(getClass().getResource("../../../FXMLs/rankingScene.fxml"));
        user_defaultS = new FXMLLoader(getClass().getResource("../../../FXMLs/userData_default.fxml"));
        user_logged_inS = new FXMLLoader(getClass().getResource("../../../FXMLs/userData_logged_in.fxml"));

        try {
            contentScreen.addScreen("home", homeS.load());
            contentScreen.addScreen("post", postS.load());
            contentScreen.addScreen("ranking", rankingS.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentScreen.activate("home");
        contentPane.setContent(contentScreen.currentScreen());

        try {
            loginWrap.addScreen("default", user_defaultS.load());
            loginWrap.addScreen("logged_in", user_logged_inS.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginWrap.activate("default");

        userData.getChildren().add(loginWrap.currentScreen());

        solvegarage.setOnMouseClicked(e -> tabClick("home"));
        homeTab.setOnMouseClicked(e -> tabClick("home"));
        categoriesTab.setOnMouseClicked(e -> tabClick("categories"));
        postTab.setOnMouseClicked(e -> tabClick("post"));
        rankingTab.setOnMouseClicked(e -> tabClick("ranking"));

        HomeController homeController = homeS.getController();
        contentPane.vvalueProperty().addListener((ObservableValue<? extends Number> ov,
                                                  Number old_val, Number new_val) -> {
            if ((double) new_val >= 0.9 && contentScreen.getName().equals("home")) {
                System.out.println("scrolled to bottom");
                ((VBox) contentScreen.currentScreen()).setMaxHeight(((VBox) contentScreen.currentScreen()).getMaxHeight() + 200 * 10);
                contentPane.setContent(contentScreen.currentScreen());
                homeController.updateProblems();
            }
        });
    }
}
