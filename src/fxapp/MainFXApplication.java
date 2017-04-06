package fxapp;


import javafx.application.Application;
import javafx.stage.Stage;

public class MainFXApplication extends Application {

    private Stage window;

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
    }
}
