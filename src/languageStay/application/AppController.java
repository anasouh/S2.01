package languageStay.application;

import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import languageStay.Country;
import languageStay.CriterionName;
import languageStay.Teenager;

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
    @FXML
    TextField nameField, forenameField, ddnField, hobbiesField;
    @FXML
    CheckBox allergyField, hostNuts, hostVege, visitorVege, visitorNuts, hostAnimalField, keepField;
    @FXML
    ChoiceBox genderField, countryField, prefGenderField;
    
    @FXML
    public void initialize() throws IOException {
        System.out.println("Initialisation...");
        if (genderField != null){
            genderField.getItems().addAll("Male", "Female");
            prefGenderField.getItems().addAll("Male", "Female");
            countryField.getItems().addAll("FRANCE", "GERMANY");
        }
    }

    private void loadScene(String name) {
        /*
         * Charger la scene correspondant au nom passé en paramètre
         * @param name : nom de la scene à charger
         */
        try {
            if (name.equals("Accueil")) {
                accueil = new Scene(loadFXML(name));
            } else if (name.equals("Ajouter")) {
                ajouter = new Scene(loadFXML(name));
            }
        } catch (IOException ie) { ie.printStackTrace();}
    }

    private void setScene(Scene s) {
        /*
         * Changer la scene actuelle
         * @param s : nouvelle scene
         */
        stage.setScene(s);
        stage.show();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        /*
         * Charger le fichier fxml correspondant au nom passé en paramètre
         * @param fxml : nom du fichier fxml à charger
         */
        FXMLLoader fxmlLoader = new FXMLLoader(AppController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void pressedDashboard(ActionEvent event) {
        /*
         * Déclenché lors de l'appui sur le bouton "Tableau de bord"
         */
        if (stage == null) stage = (Stage) dashboardButton.getScene().getWindow();
    }

    public void pressedAddTeenager(ActionEvent event) {
        /*
         * Déclenché lors de l'appui sur le bouton "Ajouter un ado"
         */
        if (stage == null) stage = (Stage) addTeenagerButton.getScene().getWindow();
        loadScene("Ajouter");
        setScene(ajouter);
    } 

    private String yesOrNo(boolean b) {
        /*
         * Retourne "yes" si b est vrai, "no" sinon
         * @param b : booléen à tester
         */
        if (b) return "yes";
        return "no";
    }

    private boolean unvalidField(Node field) {
        /*
         * Met en rouge le champ passé en paramètre et retourne false
         * @param field : champ à mettre en rouge
         */
        field.setStyle("-fx-border-color: red");
        return false;
    }

    private boolean validField(Node field) {
        /*
         * Met en vert le champ passé en paramètre et retourne true
         * @param field : champ à mettre en vert
         */
        field.setStyle("-fx-border-color: inherit");
        return true;
    }

    private boolean checkField(TextField field) {
        /*
         * Vérifie que le champ passé en paramètre n'est pas vide
         * @param field : champ à vérifier
         */
        if (field.getText().isEmpty()) {
            return unvalidField(field);
        }
        return validField(field);
    }

    private boolean checkDateField(TextField field) {
        /*
         * Vérifie que le champ passé en paramètre n'est pas vide et qu'il contient une date valide
         * @param field : champ à vérifier
         */
        if (field.getText().isEmpty()) {
            return unvalidField(field);
        }
        String[] date = field.getText().split("/");
        if (date.length != 3) {
            return unvalidField(field);
        }
        try {
            LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
        } catch (Exception e) {
            return unvalidField(field);
        }
        return validField(field);
    }

    private boolean checkChoice(ChoiceBox box) {
        /*
         * Vérifie que le champ passé en paramètre n'est pas vide
         * @param box : champ à vérifier
         */
        if (box.getValue() == null || box.getValue().toString().isEmpty()) {
            return unvalidField(box);
        }
        return validField(box);
    }

    public void saveTeenager(ActionEvent event) {
        /*
         * Déclenché lors de l'appui sur le bouton "Enregistrer" dans la scène "Ajouter"
         */
        if (checkField(nameField) && checkField(forenameField) && checkDateField(ddnField) && checkChoice(genderField) && checkChoice(countryField)) {
            System.out.println("Saving teenager...");
            String[] date = ddnField.getText().split("/");
            String food = "";
            Teenager teen = new Teenager(nameField.getText(), forenameField.getText(), LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0])), Country.valueOf(countryField.getValue().toString()));
            teen.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, yesOrNo(hostAnimalField.isSelected()));
            teen.addCriterion(CriterionName.GENDER, genderField.getValue().toString().toLowerCase());
            if (visitorNuts.isSelected()) food += "nonuts,";
            if (visitorVege.isSelected()) food += "vege,";
            if (food.length() > 0) teen.addCriterion(CriterionName.GUEST_FOOD, food.substring(0, food.length()-1));
            teen.addCriterion(CriterionName.HOST_HAS_ANIMAL, yesOrNo(hostAnimalField.isSelected()));
            food = "";
            if (hostNuts.isSelected()) food += "nonuts,";
            if (hostVege.isSelected()) food += "vege,";
            if (food.length() > 0) teen.addCriterion(CriterionName.HOST_FOOD, food.substring(0, food.length()-1));
            teen.addCriterion(CriterionName.HISTORY, yesOrNo(keepField.isSelected()));
            teen.addCriterion(CriterionName.HOBBIES, hobbiesField.getText());
            if (prefGenderField.getValue() != null) teen.addCriterion(CriterionName.PAIR_GENDER, prefGenderField.getValue().toString().toLowerCase());
            teen.purgeInvalidRequierement();
            System.out.println(teen.getNbCriterion());
            System.out.println(teen);
        }
    }
}
