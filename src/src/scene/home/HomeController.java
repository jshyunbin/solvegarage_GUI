package src.scene.home;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    VBox problemSet;

    private FXMLLoader problems;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        problems = new FXMLLoader(getClass().getResource("../../../FXMLs/problemList.fxml"));
        updateProblems();
    }

    public void updateProblems() {
        for (int i = 0; i < 10; i++) {
            //JsonObject object = SecureHttpConnection.get(SecureHttpConnection.problemsURL, );
            ProblemListController problemListController = problems.getController();
        }
    }
}
