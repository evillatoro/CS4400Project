package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.CityState;
import model.Model;
import model.POI;

public class AddNewLocationController {

    @FXML
    private TextField poiLocationNameField;
    @FXML
    private ChoiceBox<String> cityChoiceBox;
    @FXML
    private ChoiceBox<String> stateChoiceBox;
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
        cityChoiceBox.setItems(CityState.getCities());
        cityChoiceBox.setValue(CityState.getCities().get(0));

        stateChoiceBox.setItems(CityState.getStates());
        stateChoiceBox.setValue(CityState.getStates().get(0));

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
            CityState cityState = new CityState(
                    cityChoiceBox.getSelectionModel().getSelectedItem(),
                    stateChoiceBox.getSelectionModel().getSelectedItem());
            POI poiLocation = new POI(
                    poiLocationNameField.getText(),
                    cityState,
                    Integer.parseInt(zipCodeField.getText()));
            // if POI is successfully added, inform user it was successful
            if (Model.getInstance().addPOI(poiLocation)) {
                clearFields();
                // if the POI fails, notify the user
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(mainApplication.getWindow());
                alert.setTitle("POI Added");
                alert.setHeaderText("Good POI Add");
                alert.setContentText("POI was added");
                alert.showAndWait();
            } else {
                // if the POI fails, notify the user
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApplication.getWindow());
                alert.setTitle("POI Not Added");
                alert.setHeaderText("Bad POI Add");
                alert.setContentText("POI was not added," +
                        "check that they are not already in server!");
                alert.showAndWait();
            }
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
        } else {
            try {
                Integer.parseInt(zipCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid zip code!\n";
            }
        }

        if (!Model.getInstance().checkCityStateCombo(cityChoiceBox.getValue(), stateChoiceBox.getValue())) {
            errorMessage += "Not valid city state combination\n";
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
     * clears all fields
     */
    private void clearFields() {
        poiLocationNameField.clear();
        zipCodeField.clear();
    }
}
