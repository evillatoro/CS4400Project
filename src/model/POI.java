package model;

/**
 * represents a POI object
 */
public class POI {

    private String name;
    private Boolean flagged;
    private String dateFlagged;
    private String zipCode;
    private CityState cityState;

    /**
     * makes a POI with a name, flagged, date flagged, zip code, CityState object used when loading from database
     * @param name          POI name
     * @param flagged       POI flagged
     * @param dateFlagged   POI date flagged
     * @param zipCode       POI zip code
     * @param cityState     POI city and state
     */
    public POI(String name, Boolean flagged, String dateFlagged, String zipCode, CityState cityState) {
        this.name = name;
        this.flagged = flagged;
        this.dateFlagged = dateFlagged;
        this.zipCode = zipCode;
        this.cityState = cityState;
    }

    /**
     * makes a POI with a name, default not flagged, no date flagged, zip code, CityState object used to
     * store into database
     * @param name          POI name
     * @param zipCode       POI zip code
     * @param cityState     POI city and state
     */
    public POI(String name, String zipCode, CityState cityState) {
        this(name, false, null, zipCode, cityState);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Boolean flagged() {
        return flagged;
    }

    private void setFlagged(Boolean flagged) {
        this.flagged = flagged;
    }

    public String getDateFlagged() {
        return dateFlagged;
    }

    private void setDateFlagged(String dateFlagged) {
        this.dateFlagged = dateFlagged;
    }

    public String getZipCode() {
        return zipCode;
    }

    private void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public CityState getCityState() {
        return cityState;
    }

    private void setCityState(CityState cityState) {
        this.cityState = cityState;
    }
}
