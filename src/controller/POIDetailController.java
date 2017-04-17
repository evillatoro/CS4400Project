package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class POIDetailController {

    @FXML
    private TextField dataValueMin;
    @FXML
    private TextField dataValueMax;
    @FXML
    private ChoiceBox<String> typeChoiceBox;
    @FXML
    private JFXDatePicker datePickerMin;
    @FXML
    private JFXDatePicker datePickerMax;
    @FXML
    private JFXTimePicker timePickerMin;
    @FXML
    private JFXTimePicker timePickerMax;

    /**
     * link back to main application
     */
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
     * called when the user clicks apply filter
     */
    @FXML
    private void handleApplyFilterPressed(){

    }

    /**
     * called when the user clicks reset filter
     */
    @FXML
    private void handleResetFilterPressed(){
        dataValueMin.clear();
        dataValueMax.clear();
        datePickerMax.setValue(null);
        datePickerMin.setValue(null);
        timePickerMin.getEditor().clear();
        timePickerMax.getEditor().clear();
    }

    /**
     * called when the user clicks back
     */
    @FXML
    private void handleBackPressed() {
        mainApplication.displayViewPOIsScene();
    }

    /**
     * called when the user clicks flag
     */
    @FXML
    private void handleFlagPressed() {

    }

    /**
     * checks if all input is valid
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        // check if fields are empty
        if ((dataValueMin.getText() == null) ||
                (dataValueMin.getText().isEmpty())) {
            errorMessage += "No minimum data value entered!\n";
        }
        if ((dataValueMax.getText() == null) ||
                (dataValueMax.getText().isEmpty())) {
            errorMessage += "No maximum data value entered!\n";
        }
        if ((datePickerMin.getValue() == null)) {
            errorMessage += "No start date is entered!\n";
        }
        if ((datePickerMax.getValue() == null)) {
            errorMessage += "No end date is entered!\n";
        }
        if ((timePickerMin.getValue() == null)) {
            errorMessage += "No start time is entered!\n";
        }
        if ((timePickerMax.getValue() == null)) {
            errorMessage += "No end time is entered!\n";
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
