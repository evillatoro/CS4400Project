package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;

public class PendingDataPointsController {

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
     * called when the user clicks accept
     */
    @FXML
    private void handleAcceptPressed() {
    }

    /**
     * called when the user clicks reject
     */
    @FXML
    private void handleRejectPressed() {

    }

    /**
     * called when the user clicks back
     */
    @FXML
    private void handleBackPressed() {
        mainApplication.displayFunctionalityAdminScene();
    }
}
