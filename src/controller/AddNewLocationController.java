package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.DataType;
import model.Model;

public class AddNewLocationController {

    @FXML
    private TextField poiLocationNameField;
    @FXML
    private ChoiceBox cityChoiceBox;
    @FXML
    private ChoiceBox stateChoiceBox;
    @FXML
    private TextField zipCodeField;
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
     * sets the choice box with data types
     */
    @FXML
    private void initialize() {
        Model.getInstance().loadDataTypes();
        cityChoiceBox.setItems(DataType.getDataTypes());
        stateChoiceBox.setValue(DataType.getDataTypes().get(0));

    }

    /**
     * called when the user clicks back
     */
    @FXML
    private void handleBackPressed() {
        mainApplication.displayFunctionalityCityScientistScene();
    }

    /**
     * called when the user clicks submit
     */
    @FXML
    private void handleSubmitPressed() {
       if (isInputValid()) {


       }


    }
    private boolean isInputValid() {
        String errorMessage = "";

        // check if fields are empty
        if ((poiLocationNameField.getText() == null) ||
                (poiLocationNameField.getText().isEmpty())) {
            errorMessage += "No valid POILocation!\n";
        }
        if ((zipCodeField.getText() == null) ||
                (zipCodeField.getText().isEmpty())) {
            errorMessage += "No valid zip code!\n";
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

}
