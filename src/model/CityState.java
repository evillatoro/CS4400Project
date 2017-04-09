package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * represents a City State Object
 */
public class CityState {

    private String city;
    private String state;

    /** a list of all cities*/
    private static ObservableList<String> cities = FXCollections.observableArrayList();
    /** a list of all states*/
    private static ObservableList<String> states = FXCollections.observableArrayList();

    /**
     * makes a City State with a city and state
     * @param city CityState city
     * @param state CityState state
     */
    public CityState(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static ObservableList<String> getCities() {
        return cities;
    }

    public static void setCities(ObservableList<String> cities) {
        CityState.cities = cities;
    }

    public static ObservableList<String> getStates() {
        return states;
    }

    public static void setStates(ObservableList<String> states) {
        CityState.states = states;
    }
}
