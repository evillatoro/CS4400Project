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

    private SimpleStringProperty name;
    private SimpleBooleanProperty flagged;
    private SimpleStringProperty dateFlagged;
    private SimpleIntegerProperty zipCode;
    private SimpleStringProperty city;
    private SimpleStringProperty state;
    private CityState cityState;

    private SimpleIntegerProperty numberOfReports;
    private SimpleDoubleProperty moldMin;
    private SimpleDoubleProperty moldAvg;
    private SimpleDoubleProperty moldMax;

    private SimpleDoubleProperty aqMin;
    private SimpleDoubleProperty aqAvg;
    private SimpleDoubleProperty aqMax;


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
        this.name = new SimpleStringProperty(name);
        this.flagged = new SimpleBooleanProperty(flagged);
        this.dateFlagged = new SimpleStringProperty(dateFlagged);
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

    public POI() {

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

    public void setZipCode(int zipCode) {
        this.zipCode.set(zipCode);
    }

    public CityState getCityState() {
        return cityState;
    }

    public void setCityState(CityState cityState) {
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

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public boolean isFlagged() {
        return flagged.get();
    }

    public SimpleBooleanProperty flaggedProperty() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged.set(flagged);
    }

    public SimpleStringProperty dateFlaggedProperty() {
        return dateFlagged;
    }

    public SimpleIntegerProperty zipCodeProperty() {
        return zipCode;
    }

    public int getNumberOfReports() {
        return numberOfReports.get();
    }

    public SimpleIntegerProperty numberOfReportsProperty() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports.set(numberOfReports);
    }

    public double getMoldMin() {
        return moldMin.get();
    }

    public SimpleDoubleProperty moldMinProperty() {
        return moldMin;
    }

    public void setMoldMin(double moldMin) {
        this.moldMin.set(moldMin);
    }

    public double getMoldAvg() {
        return moldAvg.get();
    }

    public SimpleDoubleProperty moldAvgProperty() {
        return moldAvg;
    }

    public void setMoldAvg(double moldAvg) {
        this.moldAvg.set(moldAvg);
    }

    public double getMoldMax() {
        return moldMax.get();
    }

    public SimpleDoubleProperty moldMaxProperty() {
        return moldMax;
    }

    public void setMoldMax(double moldMax) {
        this.moldMax.set(moldMax);
    }

    public double getAqMin() {
        return aqMin.get();
    }

    public SimpleDoubleProperty aqMinProperty() {
        return aqMin;
    }

    public void setAqMin(double aqMin) {
        this.aqMin.set(aqMin);
    }

    public double getAqAvg() {
        return aqAvg.get();
    }

    public SimpleDoubleProperty aqAvgProperty() {
        return aqAvg;
    }

    public void setAqAvg(double aqAvg) {
        this.aqAvg.set(aqAvg);
    }

    public double getAqMax() {
        return aqMax.get();
    }

    public SimpleDoubleProperty aqMaxProperty() {
        return aqMax;
    }

    public void setAqMax(double aqMax) {
        this.aqMax.set(aqMax);
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

    public static ObservableList<POI> getPoisForFilter() {
        return poisForFilter;
    }

    public static void setPoisForFilter(ObservableList<POI> poisForFilter) {
        POI.poisForFilter = poisForFilter;
    }

    public static ObservableList<String> getPoisNamesForFilter() {
        return poisNamesForFilter;
    }

    public static void setPoisNamesForFilter(ObservableList<String> poisNamesForFilter) {
        POI.poisNamesForFilter = poisNamesForFilter;
    }
}
