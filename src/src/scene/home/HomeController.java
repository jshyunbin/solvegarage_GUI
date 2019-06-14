package src.scene.home;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    VBox problemSet;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        updateProblems();
    }

    public int getProblemSize() {
        return problemSet.getChildren().size();
    }

    public void updateProblems() {
        for (int i = 0; i < 10; i++) {
            //JsonObject object = SecureHttpConnection.get(SecureHttpConnection.problemsURL, );
            FXMLLoader problems = new FXMLLoader(getClass().getResource("../../../FXMLs/problemList.fxml"));
            ProblemListController problemListController = problems.getController();
            try {
                problemSet.getChildren().add(problems.load());
            } catch (IOException e) {
                System.out.println("No such file named '../../../FXMLs/problemList.fxml'");
            }
        }
    }
}