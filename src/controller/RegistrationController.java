package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Model;
import model.User;

public class RegistrationController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private ChoiceBox<String> userTypeComboBox;

    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    /**
     * setup the main application link so we can call methods there
     *
     * @param mainFXApplication  a reference (link) to our main class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

    /**
     * sets the combo box with account types
     */
    @FXML
    private void initialize() {
        userTypeComboBox.getItems().addAll("city scientist", "city official");
        userTypeComboBox.setValue("city scientist");
    }

    /**
     * called when the user clicks cancel
     */
    public void handleCreatePressed() {
        User user = new User(
                usernameField.getText(),
                passwordField.getText(),
                emailField.getText(),
                userTypeComboBox.getSelectionModel().getSelectedItem());
        Model.getInstance().addUser(user);
    }

    /**
     * called when the user clicks cancel
     */
    public void handleCancelPressed() {
        mainApplication.displayLoginScene();
    }
}
