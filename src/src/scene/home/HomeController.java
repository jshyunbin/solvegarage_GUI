package src.scene.home;

import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import src.Utils.SecureHttpConnection;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the Home tab from the main screen.
 * Shows a list of recommended problems from server.
 */
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

    /**
     * A public void method that updates the problem list.
     * GETs 10 problems from server and adds to the problemSet variable.
     */
    public void updateProblems() {
        for (int i = 0; i < 10; i++) {
            JsonObject object = null;
            try {
                object = SecureHttpConnection.get(SecureHttpConnection.problemsURL,
                        ((Integer) (getProblemSize() + 1)).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean success = object.get("success").getAsBoolean();
            FXMLLoader problems = new FXMLLoader(getClass().getResource("../../../FXMLs/problemList.fxml"));
            if (success) {
                String title, body, author, date;
                title = object.get("title").getAsString();
                body = object.get("body").getAsString();
                author = object.get("author").getAsString();
                date = object.get("date").getAsString();
                ProblemListController problemListController = problems.getController(); //used to set title and
                problemListController.setProblem(title, body, 0, "#null", date, author);
            }
            // description about the problem
            try {
                problemSet.getChildren().add(problems.load());
            } catch (IOException e) {
                System.out.println("No such file named '../../../FXMLs/problemList.fxml'");
            }
        }
    }
}
