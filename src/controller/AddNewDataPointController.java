package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import model.DataType;
import model.Model;

public class AddNewDataPointController {

    @FXML
    private ChoiceBox<String> dataTypeComboBox;

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
        Model.getInstance().loadDataTypes();
        dataTypeComboBox.setItems(DataType.getDataTypes());
        dataTypeComboBox.setValue(DataType.getDataTypes().get(0));
    }

    /**
     * called when the user clicks add new location
     */
    @FXML
    private void handleAddNewLocationPressed() {
        mainApplication.displayAddNewLocationScene();
    }

    /**
     * called when the user clicks back
     */
    @FXML
    private void handleBackPressed() {
        mainApplication.displayFunctionalityCityScientistScene();
    }
}
