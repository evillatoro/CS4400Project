package fxapp;


import controller.AddNewDataPointController;
import controller.AddNewLocationController;
import controller.FunctionalityAdminController;
import controller.FunctionalityCityOfficialController;
import controller.FunctionalityCityScientistController;
import controller.LoginController;
import controller.POIDetailController;
import controller.POIReportController;
import controller.PendingCityOfficialAccountsController;
import controller.PendingDataPointsController;
import controller.RegistrationController;
import controller.ViewPOIsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;

import java.io.IOException;

public class MainFXApplication extends Application {

    private Stage window;

    private Scene loginScene;
    private Scene registerScene;
    private Scene functionalityCityScientistScene;
    private Scene addNewDataPointScene;
    private Scene addNewLocationScene;
    private Scene functionalityAdminScene;
    private Scene pendingDataPointsScene;
    private Scene pendingCityOfficialAccountsScene;
    private Scene functionalityCityOfficialScene;
    private Scene viewPOIsScene;
    private Scene POIDetailScene;
    private Scene POIReportScene;

    /**
     * start up application
     * @param args default
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * window of application
     *
     * @return main stage of application
     */
    public Stage getWindow() {
        return window;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setTitle("CS4400 Project");
        Model.getInstance();
        //load all the scenes first
        loadLoginScene();
        loadRegisterScene();
        loadCityScientistFunctionalityScene();
        loadAddNewDataPointScene();
        loadAddNewLocationScene();
        loadFunctionalityAdminScene();
        loadPendingDataPointsScene();
        loadPendingCityOfficialAccountsScene();
        loadFunctionalityCityOfficialScene();
        loadViewPOIsScene();
        loadPOIDetailScene();
        loadPOIReportScene();

        displayLoginScene();
    }

    private void loadLoginScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/Login.fxml"));
        Pane loginScreenLayout = loader.load();

        // Give the controller access to the main app.
        LoginController controller = loader.getController();
        controller.setMainApp(this);

        loginScene = new Scene(loginScreenLayout);
    }

    private void loadRegisterScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/Registration.fxml"));
        Pane registerScreenLayout = loader.load();

        // Give the controller access to the main app.
        RegistrationController controller = loader.getController();
        controller.setMainApp(this);

        registerScene = new Scene(registerScreenLayout);
    }

    private void loadCityScientistFunctionalityScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/FunctionalityCityScientist.fxml"));
        Pane fCityScientistLayout = loader.load();

        // Give the controller access to the main app.
        FunctionalityCityScientistController controller = loader.getController();
        controller.setMainApp(this);

        functionalityCityScientistScene = new Scene(fCityScientistLayout);
    }

    private void loadAddNewDataPointScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/AddNewDataPoint.fxml"));
        Pane POIReportLayout = loader.load();

        // Give the controller access to the main app.
        AddNewDataPointController controller = loader.getController();
        controller.setMainApp(this);

        addNewDataPointScene = new Scene(POIReportLayout);
    }

    private void loadAddNewLocationScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/AddNewLocation.fxml"));
        Pane addNewLocationLayout = loader.load();

        // Give the controller access to the main app.
        AddNewLocationController controller = loader.getController();
        controller.setMainApp(this);

        addNewLocationScene = new Scene(addNewLocationLayout);
    }

    private void loadFunctionalityAdminScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/FunctionalityAdmin.fxml"));
        Pane addNewLocationLayout = loader.load();

        // Give the controller access to the main app.
        FunctionalityAdminController controller = loader.getController();
        controller.setMainApp(this);

        functionalityAdminScene = new Scene(addNewLocationLayout);
    }

    private void loadPendingDataPointsScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/PendingDataPoints.fxml"));
        Pane pendingDataPointsLayout = loader.load();

        // Give the controller access to the main app.
        PendingDataPointsController controller = loader.getController();
        controller.setMainApp(this);

        pendingDataPointsScene = new Scene(pendingDataPointsLayout);
    }

    private void loadPendingCityOfficialAccountsScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/PendingCityOfficials.fxml"));
        Pane pendingCityOfficialAccountsLayout = loader.load();

        // Give the controller access to the main app.
        PendingCityOfficialAccountsController controller = loader.getController();
        controller.setMainApp(this);

        pendingCityOfficialAccountsScene = new Scene(pendingCityOfficialAccountsLayout);
    }

    private void loadFunctionalityCityOfficialScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/FunctionalityCityOfficial.fxml"));
        Pane addNewLocationLayout = loader.load();

        // Give the controller access to the main app.
        FunctionalityCityOfficialController controller = loader.getController();
        controller.setMainApp(this);

        functionalityCityOfficialScene = new Scene(addNewLocationLayout);
    }

    private void loadViewPOIsScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/ViewPOIs.fxml"));
        Pane POIReportLayout = loader.load();

        // Give the controller access to the main app.
        ViewPOIsController controller = loader.getController();
        controller.setMainApp(this);

        viewPOIsScene = new Scene(POIReportLayout);
    }

    private void loadPOIDetailScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/POIDetail.fxml"));
        Pane POIReportLayout = loader.load();

        // Give the controller access to the main app.
        POIDetailController controller = loader.getController();
        controller.setMainApp(this);

        POIDetailScene = new Scene(POIReportLayout);
    }

    private void loadPOIReportScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/POIReport.fxml"));
        Pane POIReportLayout = loader.load();

        // Give the controller access to the main app.
        POIReportController controller = loader.getController();
        controller.setMainApp(this);

        POIReportScene = new Scene(POIReportLayout);
    }

    /**
     * display login scene
     */
    public void displayLoginScene() {
        window.setScene(loginScene);
        window.show();
    }

    /**
     * display register scene
     */
    public void displayRegisterScene() {
        window.setScene(registerScene);
        window.show();
    }

    /**
     * display functionality city scientist scene
     */
    public void displayFunctionalityCityScientistScene() {
        window.setScene(functionalityCityScientistScene);
        window.show();
    }

    /**
     * display login scene
     */
    public void displayAddNewDataPointScene() {
        window.setScene(addNewDataPointScene);
        window.show();
    }

    /**
     * display add new location scene
     */
    public void displayAddNewLocationScene() {
        window.setScene(addNewLocationScene);
        window.show();
    }

    /**
     * display functionality admin scene
     */
    public void displayFunctionalityAdminScene() {
        window.setScene(functionalityAdminScene);
        window.show();
    }

    /**
     * display pending data points scene
     */
    public void displayPendingDataPointsScene() {
        window.setScene(pendingDataPointsScene);
        window.show();
    }

    /**
     * display pending city official accounts scene
     */
    public void displayPendingCityOfficialAccountsScene() {
        window.setScene(pendingCityOfficialAccountsScene);
        window.show();
    }

    /**
     * display functionality city official scene
     */
    public void displayFunctionalityCityOfficialScene() {
        window.setScene(functionalityCityOfficialScene);
        window.show();
    }

    /**
     * display view POIs scene
     */
    public void displayViewPOIsScene() {
        window.setScene(viewPOIsScene);
        window.show();
    }

    /**
     * display POI detail scene
     */
    public void displayPOIDetailScene() {
        window.setScene(POIDetailScene);
        window.show();
    }

    /**
     * display POI report scene
     */
    public void displayPOIReportScene() {
        window.setScene(POIReportScene);
        window.show();
    }
}
