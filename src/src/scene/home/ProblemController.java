package src.scene.home;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProblemController implements Initializable {

    @FXML
    Label title, description, date, author;
    @FXML
    Button submitBtn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public void setProblem(String title, String description, String date, String author) {
        this.title.setText(title);
        this.description.setText(description);
        this.date.setText("date: " + date);
        this.author.setText("written by " + author);
    }
}
