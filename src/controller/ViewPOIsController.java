package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DataType;
import model.POI;

import java.util.ArrayList;

public class ViewPOIsController {
    @FXML
    private ChoiceBox poiLocationNameChoiceBox;
    @FXML
    private ChoiceBox<String> cityChoiceBox;
    @FXML
    private ChoiceBox<String> stateChoiceBox;
    @FXML
    private TextField zipCodeTextField;
    @FXML
    private CheckBox flaggedCheckBox;
    @FXML
    private DatePicker toDatePicker;
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private TableView poiLocationTable;
    @FXML
    private TableColumn locationNameCol;
    @FXML
    private TableColumn cityCol;
    @FXML
    private TableColumn stateCol;
    @FXML
    private TableColumn zipCodeCol;
    @FXML
    private TableColumn flaggedCol;
    @FXML
    private TableColumn dateFlaggedCol;

    ArrayList<POI> poiLocations;

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
     * sets the choice box with data type
     */
    @FXML
    private void initialize() {
        //Model.getInstance().loadDataTypes();
        poiLocations = new ArrayList<>();

        locationNameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        cityCol.setCellValueFactory(
                new PropertyValueFactory<>("city"));
        stateCol.setCellValueFactory(
                new PropertyValueFactory<>("state"));
        zipCodeCol.setCellValueFactory(
                new PropertyValueFactory<>("zipCode"));
        flaggedCol.setCellValueFactory(
                new PropertyValueFactory<>("flagged"));
        dateFlaggedCol.setCellValueFactory(
                new PropertyValueFactory<>("dateFlagged"));
        poiLocationTable.setItems(POI.getPois());

        cityChoiceBox.setItems(DataType.getDataTypes());
        stateChoiceBox.setValue(DataType.getDataTypes().get(0));
    }
    /**
     * called when user presses apply filter button
     */
   @FXML
    private void handleApplyFilterPressed() {
        if (isInputValid()) {

        }
    }
    /**
     * called when user presses reset filter button
     */
    @FXML
    private void handleResetFilterPressed() {
        zipCodeTextField.clear();
        toDatePicker.setValue(null);
        fromDatePicker.setValue(null);
    }

    /**
     * called when user presses back button
     */
    @FXML
    private void handleBackPressed() {
        mainApplication.displayFunctionalityCityOfficialScene();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        // check if fields are empty
        if ((zipCodeTextField.getText() == null) ||
                (zipCodeTextField.getText().isEmpty())) {
            errorMessage += "No valid Zip Code!\n";
        }
        if (fromDatePicker.getValue() == null) {
            errorMessage += "No starting date picked!";
        }
        if (toDatePicker.getValue() == null) {
            errorMessage += "No end date picked!";
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
