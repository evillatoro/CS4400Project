package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Datapoint;
import model.Model;

import java.util.ArrayList;

public class PendingDataPointsController {

    @FXML
    private TableView dataPointTable;
    @FXML
    private TableColumn selectCol;
    @FXML
    private TableColumn locationCol;
    @FXML
    private TableColumn dataTypeCol;
    @FXML
    private TableColumn dataValueCol;
    @FXML
    private TableColumn timeCol;
    @FXML
    private TableColumn dateCol;

    ArrayList<Datapoint> dataPoints;

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
     * sets the columns
     */
    @FXML
    public void initialize() {
        dataPoints = new ArrayList<>();

        locationCol.setCellValueFactory(
                new PropertyValueFactory<>("locationName"));
        dataTypeCol.setCellValueFactory(
                new PropertyValueFactory<>("dataType"));
        dataValueCol.setCellValueFactory(
                new PropertyValueFactory<>("dataValue"));
        timeCol.setCellValueFactory(
                new PropertyValueFactory<>("time"));
        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date"));
        dataPointTable.setItems(Datapoint.getDataPoints());


        selectCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Datapoint, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Datapoint, CheckBox> arg0) {
                Datapoint point = arg0.getValue();
                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> ov,
                                        Boolean old_val, Boolean new_val) {
                        if (new_val) {
                            dataPoints.add(point);
                        } else {
                            dataPoints.remove(point);
                        }
                    }
                });
                return new SimpleObjectProperty<CheckBox>(checkBox);

            }

        });

    }

    /**
     * called when the user clicks accept
     */
    @FXML
    private void handleAcceptPressed() {
        if(dataPoints.size() != 0) {
            dataPointTable.getItems().removeAll(dataPoints);
            Model.getInstance().acceptDataPoints(dataPoints);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = mainApplication.getWindow();
            alert.initOwner(stage);
            alert.setTitle("Accepted Data Points");
            String message = "";
            for (int i = 0; i < dataPoints.size(); i++) {
                message += dataPoints.get(i) + "\n";
            }
            alert.setHeaderText("Accepted City Data Points");
            alert.setContentText(message);
            alert.showAndWait();
            dataPoints.clear();
        }
    }

    /**
     * called when the user clicks reject
     */
    @FXML
    private void handleRejectPressed() {
        if(dataPoints.size() != 0) {
            dataPointTable.getItems().removeAll(dataPoints);
            Model.getInstance().rejectDataPoints(dataPoints);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = mainApplication.getWindow();
            alert.initOwner(stage);
            alert.setTitle("Rejected Data Points");
            String message = "";
            for (int i = 0; i < dataPoints.size(); i++) {
                message += dataPoints.get(i) + "\n";
            }
            alert.setHeaderText("Rejected Data Points");
            alert.setContentText(message);
            alert.showAndWait();
            dataPoints.clear();
        }
    }

    /**
     * called when the user clicks back
     */
    @FXML
    private void handleBackPressed() {
        mainApplication.displayFunctionalityAdminScene();
    }
}
