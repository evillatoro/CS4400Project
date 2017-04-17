package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;

public class FunctionalityCityOfficialController {

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
     * called when the user clicks filter/search POI
     */
    @FXML
    private void handleFilterSearchPOIPressed() {
        mainApplication.displayViewPOIsScene();
    }

    /**
     * called when the user clicks POI report
     */
    @FXML
    private void handlePOIReportPressed() {
        mainApplication.displayPOIReportScene();
    }

    /**
     * called when the user clicks logout
     */
    @FXML
    private void handleLogoutPressed() {
        mainApplication.displayLoginScene();
    }
}
