package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DataPoint;
import model.DataType;
import model.Model;
import model.POI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class POIDetailController {

    @FXML
    private Label poiLocationLabel;
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
    @FXML
    private TableView POIDetailTableView;
    @FXML
    private TableColumn dataTypeCol;
    @FXML
    private TableColumn dataValueCol;
    @FXML
    private TableColumn timeCol;
    @FXML
    private TableColumn dateCol;

    private POI currentPOI;

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

    @FXML
    public void initialize() {
        typeChoiceBox.setItems(DataType.getDataTypesForFilter());
        typeChoiceBox.setValue(DataType.getDataTypesForFilter().get(0));

        dataTypeCol.setCellValueFactory(
                new PropertyValueFactory<>("dataType"));
        dataValueCol.setCellValueFactory(
                new PropertyValueFactory<>("dataValue"));
        timeCol.setCellValueFactory(
                new PropertyValueFactory<>("time"));
        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date"));

        POIDetailTableView.setItems(DataPoint.getPoiDetailDataPoints());

        timePickerMin.setValue(LocalTime.now());
        timePickerMax.setValue(LocalTime.now());
    }

    /**
     * called when the user clicks apply filter
     */
    @FXML
    private void handleApplyFilterPressed(){
        if (isInputValid()) {
            System.out.println("current poi name " + currentPOI.getName());
            String query = getQuery();

            if (query != null) {
                Model.getInstance().doDataPointQuery(query);
            }
        }
    }

    /**
     * called when the user clicks reset filter
     */
    @FXML
    private void handleResetFilterPressed(){
        resetFields();
        String query = "SELECT * FROM data_point WHERE poi_name = \"" + currentPOI.getName() + "\"" +
                "AND accepted = TRUE";
        Model.getInstance().doDataPointQuery(query);
    }

    /**
     * called when the user clicks back
     */
    @FXML
    private void handleBackPressed() {
        resetFields();
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
        if ((dataValueMax.getText().length() != 0 && dataValueMax.getText().length() == 0) ||
                (dataValueMax.getText().length() == 0 && dataValueMin.getText().length() != 0)) {
            errorMessage += "Set the other value for the data value\n";
        }

        if ((dataValueMin.getText() != null && dataValueMin.getText().length() != 0) &&
                (!dataValueMin.getText().isEmpty())
                && (dataValueMax.getText() != null && dataValueMax.getText().length() != 0) &&
                (!dataValueMax.getText().isEmpty())) {
            try {
                int max = Integer.parseInt(dataValueMax.getText());
                int min = Integer.parseInt(dataValueMin.getText());
                if (min > max) {
                    errorMessage += "Second value must be larger than first value";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Not valid data values!\n";
            }
        }

        if (datePickerMin.getValue() != null && datePickerMax.getValue() != null) {
            LocalDate a = datePickerMin.getValue();
            LocalDate b = datePickerMax.getValue();
            LocalTime c = timePickerMin.getValue();
            LocalTime d = timePickerMax.getValue();
            LocalDateTime e = LocalDateTime.of(a,c);
            LocalDateTime f = LocalDateTime.of(b,d);
            if (f.compareTo(e) < 0) {
                errorMessage += "First Date and Time must be before Second Date and Time";
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
     * sets the POI that will be displayed
     * @param poi poi to be displayed
     */
    public void setCurrentPOI(POI poi) {
        if (poi != null) {
            currentPOI = poi;
            poiLocationLabel.setText(poi.getName());
            String query = "SELECT * FROM data_point WHERE poi_name = \"" + currentPOI.getName() + "\"" +
                    "AND accepted = TRUE";
            Model.getInstance().doDataPointQuery(query);
        }
    }

    private String getQuery() {
        boolean firstFilterUsed = false;
        String query = "SELECT * FROM data_point WHERE ";
        if (typeChoiceBox.getSelectionModel().getSelectedIndex() != 0) {
            query += "data_type = \"" + typeChoiceBox.getSelectionModel().getSelectedItem() +"\"";
            firstFilterUsed = true;
        }

        if (dataValueMin.getText().length() != 0 && dataValueMax.getText().length() != 0) {
            if (firstFilterUsed) {
                query += " AND data_value BETWEEN " + dataValueMin.getText() + " AND " + dataValueMax.getText();
            } else {
                firstFilterUsed = true;
                query += "data_value BETWEEN " + dataValueMin.getText() + " AND " + dataValueMax.getText();
            }
        }

        if (datePickerMin.getValue() != null && datePickerMax.getValue() != null) {
            if (firstFilterUsed) {
                query += " AND";
            } else {
                firstFilterUsed = true;
            }
            LocalTime min = timePickerMin.getValue();
            LocalTime max = timePickerMax.getValue();
            String pattern = "HH:mm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            query += "((date_of_reading > \"" + datePickerMin.getValue().toString() + "\" and date_of_reading < \"" + datePickerMax.getValue().toString() + "\")\n" +
                     "    or (date_of_reading = \"" + datePickerMin.getValue().toString() + "\" and time_of_reading >= \"" + min.format(formatter) + ":00\")\n" +
                     "    or (date_of_reading = \"" + datePickerMax.getValue().toString() + "\" and time_of_reading <= \"" + max.format(formatter) + ":00\"))";

        }
        if (firstFilterUsed) {
            query += " AND accepted = TRUE AND poi_name = \"" + currentPOI.getName() + "\"";
            System.out.println(query);
            return query;
        }
        System.out.println("No filter applied");

        return null;
    }

    private void resetFields() {
        dataValueMin.clear();
        dataValueMax.clear();
        datePickerMax.setValue(null);
        datePickerMin.setValue(null);
        timePickerMin.setValue(LocalTime.now());
        timePickerMax.setValue(LocalTime.now());
        typeChoiceBox.setValue(DataType.getDataTypesForFilter().get(0));
    }
}
