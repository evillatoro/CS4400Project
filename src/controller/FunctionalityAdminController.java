package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;

public class FunctionalityAdminController {

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
     * called when the user clicks pending data points
     */
    @FXML
    private void handlePendingDataPointsPressed() {
        mainApplication.displayPendingDataPointsScene();
    }

    /**
     * called when the user clicks pending city official accounts
     */
    @FXML
    private void handlePendingCityOfficialAccountsPressed() {
        mainApplication.displayPendingCityOfficialAccountsScene();
    }

    /**
     * called when the user clicks logout
     */
    @FXML
    private void handleLogoutPressed() {
        mainApplication.displayLoginScene();
    }
}
