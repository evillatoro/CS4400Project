package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Model;
import model.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * called when the user clicks create
     */
    public void handleCreatePressed() {
        if (isInputValid()) {
            User user = new User(
                    usernameField.getText(),
                    passwordField.getText(),
                    emailField.getText(),
                    userTypeComboBox.getSelectionModel().getSelectedItem());
            Model.getInstance().addUser(user);
        }
    }

    /**
     * validates the user input in the text fields
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        // check if fields are empty
        if ((usernameField.getText() == null) || (usernameField.getText().isEmpty())) {
            errorMessage += "Not valid username!\n";
        }
        if ((emailField.getText() == null) || (emailField.getText().isEmpty()) ||
                (isEmailValid(emailField.getText()))) {
            errorMessage += "Not valid email!\n";
        }
        if ((passwordField.getText() == null) || (passwordField.getText().isEmpty())) {
            errorMessage += "Not valid password entered!\n";
        }
        if (!passwordMatch(passwordField.getText())) {
            errorMessage += "Passwords do not match!\n";
        }
        //no error message means success / good input
        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApplication.getWindow());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    /**
     * validates the user input in the email field
     * @return true if the email is valid
     */
    private boolean isEmailValid(String email) {
        /*
        Email format: A valid email address will have following format:
        [\\w\\.-]+: Begins with word characters, (may include periods and hypens).
        @: It must have a '@' symbol after initial characters.
        ([\\w\\-]+\\.)+: '@' must follow by more alphanumeric characters (may include hypens.).
        This part must also have a "." to separate domain and subdomain names.
	    [A-Z]{2,4}$ : Must end with two to four alaphabets.
        (This will allow domain names with 2, 3 and 4 characters e.g net, com, etc.)
        */

        //Initialize reg ex for email.
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence input = email;
        //Make the comparison case-insensitive.
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()){
            return false;
        }
        return true;
    }

    /**
     * validates the password input with the confirm password
     * @return true if the passwords match
     */
    private boolean passwordMatch(String password) {
        if (passwordField.getText().equals(confirmPasswordField.getText())) {
            return true;
        }
        return false;
    }

    /**
     * clears all fields
     */
    private void clearFields() {
        usernameField.clear();
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    /**
     * called when the user clicks cancel
     */
    public void handleCancelPressed() {
        clearFields();
        mainApplication.displayLoginScene();
    }
}
