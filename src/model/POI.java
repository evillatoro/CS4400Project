package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * represents a POI object
 */
public class POI {

    private final SimpleStringProperty name;
    private final SimpleBooleanProperty flagged;
    private final SimpleStringProperty dateFlagged;
    private final SimpleIntegerProperty zipCode;
    private final SimpleStringProperty city;
    private final SimpleStringProperty state;
    private CityState cityState;

    /** a list of all the POI Locations*/
    private static ObservableList<POI> pois = FXCollections.observableArrayList();

    /** a list of all the POI Locations names*/
    private static ObservableList<String> poisNames = FXCollections.observableArrayList();

    /**
     * makes a POI with a name, flagged, date flagged, zip code, CityState object used when loading from database
     * @param name          POI name
     * @param flagged       POI flagged
     * @param dateFlagged   POI date flagged
     * @param zipCode       POI zip code
     * @param cityState     POI city and state
     */
    public POI(String name, Boolean flagged, String dateFlagged, int zipCode, CityState cityState) {
        this.name = new SimpleStringProperty(name);
        this.flagged = new SimpleBooleanProperty(flagged);
        this.dateFlagged = new SimpleStringProperty(dateFlagged);
        //this.timeFlagged = timeFlagged;
        this.zipCode = new SimpleIntegerProperty(zipCode);
        this.cityState = cityState;
        this.city = new SimpleStringProperty(cityState.getCity());
        this.state = new SimpleStringProperty(cityState.getState());
    }

    /**
     * makes a POI with a name, default not flagged, no date flagged, zip code, CityState object used to
     * store into database
     * @param name          POI name
     * @param cityState     POI city and state
     * @param zipCode       POI zip code
     */
    public POI(String name, CityState cityState, int zipCode) {
        this(name, false, null, zipCode, cityState);
    }

    public String getName() {
        return name.get();
    }

    private void setName(String name) {
        this.name.set(name);
    }

    public Boolean getFlagged() {
        return flagged.get();
    }

    public void setFlagged(Boolean flagged) {
        this.flagged.setValue(flagged);
    }

    public String getDateFlagged() {
        return dateFlagged.get();
    }

    private void setDateFlagged(String dateFlagged) {
        this.dateFlagged.set(dateFlagged);
    }

    public int getZipCode() {
        return zipCode.get();
    }

    private void setZipCode(int zipCode) {
        this.zipCode.set(zipCode);
    }

    public CityState getCityState() {
        return cityState;
    }

    private void setCityState(CityState cityState) {
        this.cityState = cityState;
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getState() {
        return state.get();
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public static ObservableList<POI> getPois() {
        return pois;
    }

    public static void setPois(ObservableList<POI> pois) {
        POI.pois = pois;
    }

    public static ObservableList<String> getPoisNames() {
        return poisNames;
    }

    public static void setPoisNames(ObservableList<String> poisNames) {
        POI.poisNames = poisNames;
    }
}
