package fxapp;


import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFXApplication extends Application {

    private Stage window;

    private Scene loginScene;

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
        loadLoginScene();
        displayLoginScene();
    }

    private void loadLoginScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/Login.fxml"));
        AnchorPane loginScreenLayout = loader.load();

        // Give the controller access to the main app.
        LoginController controller = loader.getController();
        controller.setMainApp(this);

        loginScene = new Scene(loginScreenLayout);
    }

    /**
     * display login scene
     */
    public void displayLoginScene() {
        window.setScene(loginScene);
        window.show();
    }
}
