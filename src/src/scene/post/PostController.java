package src.scene.post;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class PostController implements Initializable {
    @FXML
    TextField title, tags;
    @FXML
    TextArea problemDescription, answer;
    @FXML
    Label number;
    @FXML
    Button submitBtn;
    @FXML
    Slider slider;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Bindings.bindBidirectional(number.textProperty(), slider.valueProperty(), new NumberStringConverter());

        submitBtn.setOnMouseClicked(e -> submit());
    }

    private void submit() {
        //TODO: add POST request to the server
    }
}
