package controller;

import fxapp.MainFXApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.CityOfficial;
import model.CityState;
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
    @FXML
    private ChoiceBox<String> cityComboBox;
    @FXML
    private ChoiceBox<String> stateComboBox;
    @FXML
    private TextField titleField;

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
        //Model.getInstance().loadCityStates();
        cityComboBox.setItems(CityState.getCities());
        cityComboBox.setValue(CityState.getCities().get(0));

        stateComboBox.setItems(CityState.getStates());
        stateComboBox.setValue(CityState.getStates().get(0));

        userTypeComboBox.getItems().addAll("city scientist", "city official");
        userTypeComboBox.setValue("city scientist");
        userTypeComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> options, String oldValue, String newValue) {
                System.out.println(newValue);
                if(newValue.equals("city official")) {
                    System.out.println("add co fields");
                    disableFields(false);
                } else {
                    System.out.println("disable co fields");
                    disableFields(true);
                }
            }
        });

        disableFields(true);
    }

    private void disableFields(boolean disable) {
        cityComboBox.setDisable(disable);
        stateComboBox.setDisable(disable);
        titleField.setDisable(disable);
    }

    /**
     * called when the user clicks create
     */
    public void handleCreatePressed() {
        if (isInputValid()) {
            User user;
            if (userTypeComboBox.getValue().equals("city official")) {
                CityState cityState = new CityState(cityComboBox.getValue(), stateComboBox.getValue());
                user = new CityOfficial(
                        usernameField.getText(),
                        passwordField.getText(),
                        emailField.getText(),
                        titleField.getText(),
                        cityState);
            } else {
                user = new User(
                        usernameField.getText(),
                        passwordField.getText(),
                        emailField.getText(),
                        userTypeComboBox.getSelectionModel().getSelectedItem());
            }
            // if user is successfully added, take them to login screen
            if (Model.getInstance().addUser(user)) {
                clearFields();
                mainApplication.displayLoginScene();
            } else {
                // if the add fails, notify the user
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApplication.getWindow());
                alert.setTitle("Profile Not Added");
                alert.setHeaderText("Bad Profile Add");
                alert.setContentText("Profile was not added," +
                        "check that they are not already in server!");

                alert.showAndWait();
            }
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

        if (userTypeComboBox.getValue().equals("city official")) {
            if ((titleField.getText() == null) || (titleField.getText().isEmpty())) {
                errorMessage += "Not valid title!\n";
            }
            if (!Model.getInstance().checkCityStateCombo(cityComboBox.getValue(), stateComboBox.getValue())) {
                errorMessage += "Not valid city state combination\n";
            }
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
        [\\w\\.-]+: Begins with word characters, (may include periods and hyphens).
        @: It must have a '@' symbol after initial characters.
        ([\\w\\-]+\\.)+: '@' must follow by more alphanumeric characters (may include hyphens.).
        This part must also have a "." to separate domain and subdomain names.
	    [A-Z]{2,4}$ : Must end with two to four alphabets.
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
        titleField.clear();
    }

    /**
     * called when the user clicks cancel
     */
    public void handleCancelPressed() {
        clearFields();
        mainApplication.displayLoginScene();
    }
}
