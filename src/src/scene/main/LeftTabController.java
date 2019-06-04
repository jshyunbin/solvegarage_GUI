package src.scene.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class LeftTabController implements Initializable {
    @FXML Label discoverTab, categoriesTab, postTab, rankingTab;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        discoverTab.setOnMouseClicked(e -> Controller.tabClick("discover"));
        categoriesTab.setOnMouseClicked(e -> Controller.tabClick("categories"));
        postTab.setOnMouseClicked(e -> Controller.tabClick("post"));
        rankingTab.setOnMouseClicked(e -> Controller.tabClick("ranking"));
    }
}
