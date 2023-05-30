package languageStay.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AppController {
    @FXML
    Label counter;

    public void initialize() {
        System.out.println("Initialisation...");
    }

    public void pressedButtonMoins(ActionEvent event) {
        int newValue = Integer.parseInt(counter.getText()) - 1;
        counter.setText("" + newValue);
    }

    public void pressedButtonPlus(ActionEvent event) {
        int newValue = Integer.parseInt(counter.getText()) + 1;
        counter.setText("" + newValue);
    }
}
