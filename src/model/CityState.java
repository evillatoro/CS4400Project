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
    /** a list of all cities*/
    private static ObservableList<String> citiesForFilter = FXCollections.observableArrayList();
    /** a list of all states*/
    private static ObservableList<String> statesForFilter = FXCollections.observableArrayList();

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

    public String getState() {
        return state;
    }

    public static ObservableList<String> getCities() {
        return cities;
    }

    public static ObservableList<String> getStates() {
        return states;
    }

    public static ObservableList<String> getCitiesForFilter() {
        return citiesForFilter;
    }

    public static ObservableList<String> getStatesForFilter() {
        return statesForFilter;
    }
}
