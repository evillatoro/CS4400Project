package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;
import model.User;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

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
     * called when the user clicks register
     */
    @FXML
    private void handleRegisterPressed() {
        clearFields();
        mainApplication.displayRegisterScene();
    }

    /**
     * called when the user clicks login
     */
    @FXML
    private void handleLoginPressed() {
        if (isInputValid()) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            User userToLogin = Model.getInstance().searchForProfile(username, password);
            if (userToLogin != null) {
                clearFields();
                switch (userToLogin.getUser_type()) {
                    case "city scientist":
                        mainApplication.displayFunctionalityCityScientistScene();
                        break;
                    case "admin":
                        mainApplication.displayFunctionalityAdminScene();
                        break;
                    default:
                        mainApplication.displayFunctionalityCityOfficialScene();
                        break;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                Stage stage = mainApplication.getWindow();
                alert.initOwner(stage);
                alert.setTitle("Error");
                alert.setHeaderText("wrong username or password or you have not been approved");
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
        if ((usernameField.getText() == null) ||
                (usernameField.getText().isEmpty())) {
            errorMessage += "No valid username!\n";
        }
        if ((passwordField.getText() == null) ||
                (passwordField.getText().isEmpty())) {
            errorMessage += "No valid password entered!\n";
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
     * clears fields
     */
    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
    }
}
