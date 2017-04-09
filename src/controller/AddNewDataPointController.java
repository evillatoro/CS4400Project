package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.DataType;
import model.Datapoint;
import model.Model;
import model.POI;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddNewDataPointController {

    @FXML
    private ChoiceBox<String> dataTypeComboBox;
    @FXML
    private JFXDatePicker datePickerNewDataPoint;
    @FXML
    private JFXTimePicker timePickerNewDataPoint;
    @FXML
    private ChoiceBox<String> POILocationNameComboBox;
    @FXML
    private TextField dataValueNewDataPoint;

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
     * sets the combo box with data types
     */
    @FXML
    public void initialize() {
        //Model.getInstance().loadDataTypes();
        dataTypeComboBox.setItems(DataType.getDataTypes());
        dataTypeComboBox.setValue(DataType.getDataTypes().get(0));

        POILocationNameComboBox.setItems(POI.getPoisNames());
        POILocationNameComboBox.setValue(POI.getPoisNames().get(0));

        resetPickers();

    }

    /**
     * called when the user clicks add new location
     */
    @FXML
    private void handleAddNewLocationPressed() {
        clearFields();
        mainApplication.displayAddNewLocationScene();
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
            Datapoint datapoint = new Datapoint(
                    POILocationNameComboBox.getValue(),
                    datePickerNewDataPoint.getValue().toString(),
                    timePickerNewDataPoint.getValue().toString(),
                    Integer.parseInt(dataValueNewDataPoint.getText()),
                    dataTypeComboBox.getValue()
            );
            // if POI is successfully added, inform user it was successful
            if (Model.getInstance().addDataPoint(datapoint)) {
                clearFields();
                // if the POI fails, notify the user
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(mainApplication.getWindow());
                alert.setTitle("Data Point Added");
                alert.setHeaderText("Good Data Point Add");
                alert.setContentText("Data Point was added");
                alert.showAndWait();
            } else {
                // if the POI fails, notify the user
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApplication.getWindow());
                alert.setTitle("Data Point Not Added");
                alert.setHeaderText("Bad Data Point Add");
                alert.setContentText("Data Point was not added," +
                        "check that they are not already in server!");
                alert.showAndWait();
            }
        }

    }

    /**
     * checks if input is valid
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        // check if fields are empty
        if ((dataValueNewDataPoint.getText() == null) ||
                (dataValueNewDataPoint.getText().isEmpty())) {
            errorMessage += "No data value entered!\n";
        } else {
            try {
                Integer.parseInt(dataValueNewDataPoint.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid value entered!\n";
            }
        }


        if ((datePickerNewDataPoint.getValue() == null)) {
            errorMessage += "No date is entered!\n";
        }
        if ((timePickerNewDataPoint.getValue() == null)) {
            errorMessage += "No time is entered!\n";
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
        dataValueNewDataPoint.clear();
        resetPickers();
    }

    private void resetPickers() {
        LocalDate dateNow = LocalDate.now();
        datePickerNewDataPoint.setValue(dateNow);

        LocalTime timeNow = LocalTime.now();
        timePickerNewDataPoint.setValue(LocalTime.of(timeNow.getHour(),timeNow.getMinute()));
    }
}
