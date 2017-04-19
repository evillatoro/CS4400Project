package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
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

    private final SimpleIntegerProperty numberOfReports;
    private final SimpleDoubleProperty moldMin;
    private final SimpleDoubleProperty moldAvg;
    private final SimpleDoubleProperty moldMax;

    private final SimpleDoubleProperty aqMin;
    private final SimpleDoubleProperty aqAvg;
    private final SimpleDoubleProperty aqMax;

    /** a list of all the POI Locations*/
    private static ObservableList<POI> pois = FXCollections.observableArrayList();

    /** a list of all the POI Locations names*/
    private static ObservableList<String> poisNames = FXCollections.observableArrayList();

    /** a list of all the POI Locations for view poi screen*/
    private static ObservableList<POI> poisForFilter = FXCollections.observableArrayList();

    /** a list of all the POI Locations names for view poi screen*/
    private static ObservableList<String> poisNamesForFilter = FXCollections.observableArrayList();

    /**
     * makes a POI with a name, flagged, date flagged, zip code, CityState object used when loading from database
     * @param name          POI name
     * @param flagged       POI flagged
     * @param dateFlagged   POI date flagged
     * @param zipCode       POI zip code
     * @param cityState     POI city and state
     */
    public POI(String name, Boolean flagged, String dateFlagged, int zipCode, CityState cityState) {
        this(name, cityState.getCity(), cityState.getState(), zipCode, dateFlagged, flagged, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0);
    }

    /**
     * makes a POI with a name, default not flagged, no date flagged, zip code, CityState object used to
     * store into database
     * @param name          POI name
     * @param cityState     POI city and state
     * @param zipCode       POI zip code
     */
    public POI(String name, CityState cityState, int zipCode) {
        this(name, cityState.getCity(), cityState.getState(), zipCode, null, null, 0.0, 0.0, 0.0, 0.0, 0.0,0.0,0);
    }

    /**
     * used when generating POI Report
     * @param name
     * @param city
     * @param state
     * @param moldMin
     * @param moldAvg
     * @param moldMax
     * @param numberOfReports
     * @param flagged
     */
    public POI(String name, String city, String state, int zipCode, String dateFlagged, Boolean flagged, Double moldMin, Double moldAvg, Double moldMax, Double aqMin, Double aqAvg, Double aqMax, int numberOfReports) {
        this.name = new SimpleStringProperty(name);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
        this.zipCode = new SimpleIntegerProperty(zipCode);
        this.dateFlagged = new SimpleStringProperty(dateFlagged);
        this.moldMin = new SimpleDoubleProperty(moldMin);
        this.moldAvg = new SimpleDoubleProperty(moldAvg);
        this.moldMax = new SimpleDoubleProperty(moldMax);
        this.aqMin = new SimpleDoubleProperty(aqMin);
        this.aqAvg = new SimpleDoubleProperty(aqAvg);
        this.aqMax = new SimpleDoubleProperty(aqMax);
        this.numberOfReports = new SimpleIntegerProperty(numberOfReports);
        this.flagged = new SimpleBooleanProperty(flagged);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
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

    public void setDateFlagged(String dateFlagged) {
        this.dateFlagged.set(dateFlagged);
    }

    public int getZipCode() {
        return zipCode.get();
    }

    public CityState getCityState() {
        return cityState;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public int getNumberOfReports() {
        return numberOfReports.get();
    }

    public double getMoldMin() {
        return moldMin.get();
    }

    public double getMoldAvg() {
        return moldAvg.get();
    }

    public double getMoldMax() {
        return moldMax.get();
    }

    public double getAqMin() {
        return aqMin.get();
    }

    public double getAqAvg() {
        return aqAvg.get();
    }

    public double getAqMax() {
        return aqMax.get();
    }

    public static ObservableList<POI> getPois() {
        return pois;
    }

    public static ObservableList<String> getPoisNames() {
        return poisNames;
    }

    public static ObservableList<POI> getPoisForFilter() {
        return poisForFilter;
    }

    public static ObservableList<String> getPoisNamesForFilter() {
        return poisNamesForFilter;
    }
}
