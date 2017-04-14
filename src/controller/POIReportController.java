package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.POI;

public class POIReportController {

    @FXML
    private TableView poiTable;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn cityCol;
    @FXML
    private TableColumn stateCol;
    @FXML
    private TableColumn moldMinCol;
    @FXML
    private TableColumn moldAvgCol;
    @FXML
    private TableColumn moldMaxCol;
    @FXML
    private TableColumn aqMinCol;
    @FXML
    private TableColumn aqMaxCol;
    @FXML
    private TableColumn aqAvgCol;
    @FXML
    private TableColumn countCol;
    @FXML
    private TableColumn flaggedCol;

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
     * sets the columns
     */
    @FXML
    public void initialize() {

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        cityCol.setCellValueFactory(
                new PropertyValueFactory<>("city"));
        stateCol.setCellValueFactory(
                new PropertyValueFactory<>("state"));
        moldMinCol.setCellValueFactory(
                new PropertyValueFactory<>("moldMin"));
        moldAvgCol.setCellValueFactory(
                new PropertyValueFactory<>("moldAvg"));
        moldMaxCol.setCellValueFactory(
                new PropertyValueFactory<>("moldMax"));
        aqMinCol.setCellValueFactory(
                new PropertyValueFactory<>("aqMin"));
        aqAvgCol.setCellValueFactory(
                new PropertyValueFactory<>("aqAvg"));
        aqMaxCol.setCellValueFactory(
                new PropertyValueFactory<>("aqMax"));

        countCol.setCellValueFactory(
                new PropertyValueFactory<>("numberOfReports"));
        flaggedCol.setCellValueFactory(
                new PropertyValueFactory<>("flagged"));
        poiTable.setItems(POI.getPois());
    }

    /**
     * called when the user clicks back
     */
    @FXML
    private void handleBackPressed() {
        mainApplication.displayFunctionalityCityOfficialScene();
    }
}
