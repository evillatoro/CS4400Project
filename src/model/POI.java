package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * represents a POI object
 */
public class POI {

    private String name;
    private Boolean flagged;
    private String dateFlagged;
    private String timeFlagged;
    private int zipCode;
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
    public POI(String name, Boolean flagged, String dateFlagged, String timeFlagged, int zipCode, CityState cityState) {
        this.name = name;
        this.flagged = flagged;
        this.dateFlagged = dateFlagged;
        this.timeFlagged = timeFlagged;
        this.zipCode = zipCode;
        this.cityState = cityState;
    }

    /**
     * makes a POI with a name, default not flagged, no date flagged, zip code, CityState object used to
     * store into database
     * @param name          POI name
     * @param cityState     POI city and state
     * @param zipCode       POI zip code
     */
    public POI(String name, CityState cityState, int zipCode) {
        this(name, false, null, null, zipCode, cityState);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Boolean getFlagged() {
        return flagged;
    }

    public void setFlagged(Boolean flagged) {
        this.flagged = flagged;
    }

    public String getDateFlagged() {
        return dateFlagged;
    }

    private void setDateFlagged(String dateFlagged) {
        this.dateFlagged = dateFlagged;
    }

    public String getTimeFlagged() {
        return timeFlagged;
    }

    public void setTimeFlagged(String timeFlagged) {
        this.timeFlagged = timeFlagged;
    }

    public int getZipCode() {
        return zipCode;
    }

    private void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public CityState getCityState() {
        return cityState;
    }

    private void setCityState(CityState cityState) {
        this.cityState = cityState;
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
