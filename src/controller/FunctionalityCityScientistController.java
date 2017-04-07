package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;

public class FunctionalityCityScientistController {

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
     * called when the user clicks add new data point
     */
    @FXML
    private void handleAddNewDataPointPressed() {
        mainApplication.displayAddNewDataPointScene();
    }

    /**
     * called when the user clicks add new location
     */
    @FXML
    private void handleAddNewLocationPressed() {
        mainApplication.displayAddNewLocationScene();
    }

    /**
     * called when the user clicks logout
     */
    @FXML
    private void handleLogoutPressed() {
        mainApplication.displayLoginScene();
    }
}
