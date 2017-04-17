package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * represents a City Official Object
 */
public class CityOfficial extends User {

    private final SimpleStringProperty title;
    private final SimpleStringProperty city;
    private final SimpleStringProperty state;

    //private String title;
    private Boolean approved;
    private CityState cityState;

    /** a list of all the city officials*/
    private static ObservableList<CityOfficial> pendingCityOfficials = FXCollections.observableArrayList();

    /**
     * makes a City Official with a username, password, email, title, CityState object, and default approved false
     * used to store into database
     * @param username  City Official username
     * @param password  City Official password
     * @param email     City Official email
     * @param title     City Official title
     * @param cityState City Official city and state
     */
    public CityOfficial(String username, String password, String email, String title, CityState cityState) {
        this (username, password, email, title, cityState, false);
    }

    /**
     * makes a City Official with a username, password, email, title, CityState Object, and approved status
     * used when loading from database
     * @param username  City Official username
     * @param password  City Official password
     * @param email     City Official email
     * @param title     City Official title
     * @param cityState City Official city and state
     */
    public CityOfficial(String username, String password, String email, String title, CityState cityState, boolean approved) {
        super(username, password, email, "city official");
        this.title = new SimpleStringProperty(title);
        this.cityState = cityState;
        this.approved = approved;
        this.city = new SimpleStringProperty(cityState.getCity());
        this.state = new SimpleStringProperty(cityState.getState());
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
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

    public CityState getCityState() {
        return cityState;
    }

    public void setCityState(CityState cityState) {
        this.cityState = cityState;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public static ObservableList<CityOfficial> getPendingCityOfficials() {
        return pendingCityOfficials;
    }

    public static void setPendingCityOfficials(ObservableList<CityOfficial> pendingCityOfficials) {
        CityOfficial.pendingCityOfficials = pendingCityOfficials;
    }

    @Override
    public String toString() {
        return getUsername();
    }
}
