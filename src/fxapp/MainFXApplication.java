package fxapp;


import controller.LoginController;
import controller.RegistrationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFXApplication extends Application {

    private Stage window;

    private Scene loginScene;
    private Scene registerScene;

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

        //load all the scenes first
        loadLoginScene();
        loadRegisterScene();
        //TODO: load(NameOfScene)Scene load any new scenes with that format
        //TODO: also make a corresponding display(NameOfScene)Scene method with that format
        //TODO: do not forget to go into the Controller and add the setMainApp method
        //TODO: make sure to add the controller to the appropriate .fxml file (see Login.fxml for example)
        //TODO: scroll all the way to the right to see how a controller is added

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
        loader.setLocation(MainFXApplication.class.getResource("../view/NewUserRegistration.fxml"));
        Pane registerScreenLayout = loader.load();

        // Give the controller access to the main app.
        RegistrationController controller = loader.getController();
        controller.setMainApp(this);

        registerScene = new Scene(registerScreenLayout);
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
}
