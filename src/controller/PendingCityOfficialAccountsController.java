package controller;

import fxapp.MainFXApplication;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.CityOfficial;
import model.Model;

import java.util.ArrayList;

public class PendingCityOfficialAccountsController {

    @FXML
    private TableView cityOfficialTable;
    @FXML
    private TableColumn selectCol;
    @FXML
    private TableColumn usernameCol;
    @FXML
    private TableColumn emailCol;
    @FXML
    private TableColumn cityCol;
    @FXML
    private TableColumn stateCol;
    @FXML
    private TableColumn titleCol;

    ArrayList<CityOfficial> cityOfficials;


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
        // //Model.getInstance().loadPendingCityOfficials();
        cityOfficials = new ArrayList<>();

        usernameCol.setCellValueFactory(
                new PropertyValueFactory<>("username"));
        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        cityCol.setCellValueFactory(
                new PropertyValueFactory<>("city"));
        stateCol.setCellValueFactory(
                new PropertyValueFactory<>("state"));
        titleCol.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        cityOfficialTable.setItems(CityOfficial.getCityOfficials());


        selectCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CityOfficial, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(
                    TableColumn.CellDataFeatures<CityOfficial, CheckBox> arg0) {
                CityOfficial user = arg0.getValue();

                CheckBox checkBox = new CheckBox();

                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> ov,
                                        Boolean old_val, Boolean new_val) {
                        if (new_val) {
                            cityOfficials.add(user);
                        } else {
                            cityOfficials.remove(user);
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
        if(cityOfficials.size() != 0) {
            cityOfficialTable.getItems().removeAll(cityOfficials);
            Model.getInstance().acceptCityOfficialAccounts(cityOfficials);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = mainApplication.getWindow();
            alert.initOwner(stage);
            alert.setTitle("Accepted City Official Accounts");
            String message = "";
            for (int i = 0; i < cityOfficials.size(); i++) {
                message += cityOfficials.get(i) + "\n";
            }
            alert.setHeaderText("Accepted City Official Accounts");
            alert.setContentText(message);
            alert.showAndWait();
            cityOfficials.clear();
        }
    }

    /**
     * called when the user clicks reject
     */
    @FXML
    private void handleRejectPressed() {
        if(cityOfficials.size() != 0) {
            cityOfficialTable.getItems().removeAll(cityOfficials);
            Model.getInstance().rejectCityOfficialAccounts(cityOfficials);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = mainApplication.getWindow();
            alert.initOwner(stage);
            alert.setTitle("Rejected City Official Accounts");
            String message = "";
            for (int i = 0; i < cityOfficials.size(); i++) {
               message += cityOfficials.get(i) + "\n";
            }
            alert.setHeaderText("Rejected City Official Accounts");
            alert.setContentText(message);
            alert.showAndWait();
            cityOfficials.clear();
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
