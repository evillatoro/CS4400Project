package controller;

import fxapp.MainFXApplication;

public class RegistrationController {

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

    //TODO: make handleCancelPressed (look at LoginController as guide)
    public void handleCancelPressed() {
        mainApplication.displayLoginScene();
    }
    //TODO: make sure to link the Button to the action (go to Login.fxml and see how onAction works


}
