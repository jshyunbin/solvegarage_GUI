package src.scene.home;

import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import src.Utils.SecureHttpConnection;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;
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
            Node temp = null;
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
                title = Base64.getDecoder().decode(object.get("title").getAsString()).toString();
                author = object.get("author").getAsString();
                body = Base64.getDecoder().decode(object.get("body").getAsString()).toString();
                date = object.get("date").getAsString();
                try {
                    temp = problems.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ProblemListController problemListController = problems.getController();
                System.out.println(title);
                System.out.println(author);
                System.out.println(body);
                System.out.println(date);
                problemListController.setProblem(title, body, 10, "#null", date, author);
                System.out.println("success on getting problems");
            }
            problemSet.getChildren().add(temp);
        }
    }
}
