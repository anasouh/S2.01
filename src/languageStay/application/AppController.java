package languageStay.application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AppController {
    Stage stage;
    Scene ajouter;
    Scene accueil;

    @FXML
    Label accueilTitle;
    @FXML
    Button dashboardButton;
    @FXML
    Button addTeenagerButton;

    public void initialize() throws IOException {
        System.out.println("Initialisation...");
    }

    private void loadScene(String name) {
        try {
            if (name.equals("Accueil")) {
                accueil = new Scene(loadFXML(name));
            } else if (name.equals("Ajouter")) {
                ajouter = new Scene(loadFXML(name));
            }
        } catch (IOException ie) { ie.printStackTrace();}
    }

    private void setScene(Scene s) {
        stage.setScene(s);
        stage.show();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /*public void pressedButtonMoins(ActionEvent event) {
        int newValue = Integer.parseInt(counter.getText()) - 1;
        counter.setText("" + newValue);
    }

    public void pressedButtonPlus(ActionEvent event) {
        int newValue = Integer.parseInt(counter.getText()) + 1;
        counter.setText("" + newValue);
    }*/

    public void pressedDashboard(ActionEvent event) {
        if (stage == null) stage = (Stage) dashboardButton.getScene().getWindow();
    }

    public void pressedAddTeenager(ActionEvent event) {
        if (stage == null) stage = (Stage) addTeenagerButton.getScene().getWindow();
        System.out.println("AAAAAAAAAAAAAA");
        loadScene("ajouter");
        setScene(ajouter);
    } 

}
