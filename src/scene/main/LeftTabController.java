package scene.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class LeftTabController implements Initializable {
    @FXML Label discoverTab, categoriesTab, postTab, rankingTab;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        discoverTab.setOnMouseClicked(e -> tabClick("discover"));
        categoriesTab.setOnMouseClicked(e -> tabClick("categories"));
        postTab.setOnMouseClicked(e -> tabClick("post"));
        rankingTab.setOnMouseClicked(e -> tabClick("ranking"));
    }

    private void tabClick(String name) {
        System.out.println(name);
    }
}
