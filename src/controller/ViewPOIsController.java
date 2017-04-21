package controller;

import fxapp.MainFXApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CityState;
import model.Model;
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
        poiLocationTable.setItems(POI.getPoisForFilter());
        poiLocationNameChoiceBox.setItems(POI.getPoisNamesForFilter());

        cityChoiceBox.setItems(CityState.getCitiesForFilter());
        stateChoiceBox.setItems(CityState.getStatesForFilter());

        flaggedCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    fromDatePicker.setDisable(false);
                    toDatePicker.setDisable(false);
                } else {
                    fromDatePicker.setDisable(true);
                    toDatePicker.setDisable(true);
                }
            }
        });

        fromDatePicker.setDisable(true);
        toDatePicker.setDisable(true);

        poiLocationTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            POI currentPOISelected = (POI) newSelection;
            mainApplication.displayPOIDetailScene(currentPOISelected);
        });
    }

    /**
     * selects none selected
     */
    public void setNoneSelected() {
        poiLocationNameChoiceBox.getSelectionModel().select(0);
        cityChoiceBox.getSelectionModel().select(0);
        stateChoiceBox.getSelectionModel().select(0);
    }
    /**
     * called when user presses apply filter button
     */
    @FXML
    private void handleApplyFilterPressed() {
        if (isInputValid()) {
            String query = getQuery();
            Model.getInstance().doQuery(query);
        }
    }
    /**
     * called when user presses reset filter button
     */
    @FXML
    private void handleResetFilterPressed() {
        Model.getInstance().loadPOILocations();
        zipCodeTextField.clear();
        toDatePicker.setValue(null);
        fromDatePicker.setValue(null);
        poiLocationNameChoiceBox.getSelectionModel().select(0);
        cityChoiceBox.getSelectionModel().select(0);
        stateChoiceBox.getSelectionModel().select(0);
        flaggedCheckBox.setSelected(false);
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

        if ((zipCodeTextField.getText() != null && zipCodeTextField.getText().length() != 0) &&
                (!zipCodeTextField.getText().isEmpty())) {
            try {
                Integer.parseInt(zipCodeTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid zip code!\n";
            }
        }
        if (toDatePicker.getValue() != null && fromDatePicker.getValue() != null) {
            int compare = toDatePicker.getValue().compareTo(fromDatePicker.getValue());
            if (compare < 0) {
                errorMessage += "Please select a earlier date for the first date\n";
                errorMessage += "or later date for the second date!\n";
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

    private String getQuery() {
        boolean firstFilterUsed = false;
        String query = "SELECT * FROM poi WHERE ";
        if (poiLocationNameChoiceBox.getSelectionModel().getSelectedIndex() != 0) {
            query += "location_name = \"" + poiLocationNameChoiceBox.getSelectionModel().getSelectedItem() + "\"";
            firstFilterUsed = true;
        }
        if (cityChoiceBox.getSelectionModel().getSelectedIndex() != 0) {
            if (firstFilterUsed) {
                query += " AND city = \"" + cityChoiceBox.getSelectionModel().getSelectedItem() +"\"";
            } else {
                query += "city = \"" + cityChoiceBox.getSelectionModel().getSelectedItem() +"\"";
                firstFilterUsed = true;
            }
        }

        if (stateChoiceBox.getSelectionModel().getSelectedIndex() != 0) {
            if (firstFilterUsed) {
                query += " AND state = \"" + stateChoiceBox.getSelectionModel().getSelectedItem() +"\"";
            } else {
                query += "state = \"" + stateChoiceBox.getSelectionModel().getSelectedItem() +"\"";
                firstFilterUsed = true;
            }
        }

        if ((zipCodeTextField.getText() != null && zipCodeTextField.getText().length() != 0) &&
                (!zipCodeTextField.getText().isEmpty())) {
            if (firstFilterUsed) {
                query += " AND zip_code = \"" + zipCodeTextField.getText() +"\"";
            } else {
                query += "zip_code = \"" + zipCodeTextField.getText() +"\"";
                firstFilterUsed = true;
            }
        }

        if (flaggedCheckBox.isSelected()) {
            if (firstFilterUsed) {
                query += " AND flag = TRUE";
            } else {
                query += "flag = TRUE";
            }
        } else {
            if (firstFilterUsed) {
                query += " AND flag = FALSE";
            } else {
                query += "flag = FALSE";
            }
        }

        if (toDatePicker.getValue() != null && fromDatePicker.getValue() != null) {
            query += " AND date_flagged BETWEEN \'" + fromDatePicker.getValue().toString() + "\' AND \'" + toDatePicker.getValue().toString() + "\'";
        }

        System.out.println(query);
        return query;
    }
}
