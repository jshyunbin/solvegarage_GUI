package src.scene.home;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProblemListController implements Initializable {

    @FXML
    Label title, description, recommends, tags;


    @Override
    public void initialize(URL args0, ResourceBundle args1) {

    }

    public void setProblem(String title, String description, int recommends, String tags) {
        this.title.setText(title);
        this.description.setText(description);
        this.recommends.setText(((Integer) recommends).toString());
        this.tags.setText(tags);
    }
}
