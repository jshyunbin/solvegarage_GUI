package src.scene.home;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import src.scene.main.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class ProblemListController implements Initializable {

    @FXML
    Label title, description, recommends, tags;
    @FXML
    Pane problemPane;
    @FXML
    ImageView recommend;

    private String author, date;


    @Override
    public void initialize(URL args0, ResourceBundle args1) {
        problemPane.setOnMouseClicked(e -> openProblem());
    }

    private void openProblem() {
        Controller.setProblem(title.getText(), description.getText(), date, author);
    }

    public void setProblem(String title, String description, int recommends, String tags, String date, String author) {
        this.title.setText(title);
        this.description.setText(description);
        this.recommends.setText(((Integer) recommends).toString());
        this.tags.setText(tags);
        this.date = date;
        this.author = author;
    }
}
