package model;

import java.util.ArrayList;

public final class Model {

    private static final Model instance = new Model();
    public static Model getInstance() {
        return instance;
    }

    /** database of project*/
    private final Database database;

    /** remember the currently logged in user*/
    private User loggedInUser;

    private Model() {
        database = new Database();
        loadCityStates();
        loadPendingCityOfficials();
        loadDataTypes();
        loadPOILocations();
        loadPendingDataPoints();
    }

    /**
     * add a user to the database
     * @param user the user to add to the database
     * @return true if user added, false if not added
     */
    public boolean addUser(User user) {
        if (database.addUser(user)) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    /**
     * add a poi to the database
     * @param poi the poi to add to the database
     * @return true if poi added, false if not added
     */
    public boolean addPOI(POI poi) {
        return database.addPOIToDatabase(poi);
    }

    /**
     * add a dataPoint to the database
     * @param dataPoint the dataPoint to add to the database
     * @return true if dataPoint added, false if not added
     */
    public boolean addDataPoint(DataPoint dataPoint) {
        return database.addDataPointToDatabase(dataPoint);
    }

    /**
     * searches for a user
     * @param username username of user
     * @param password password of user
     * @return if found returns the user, otherwise null
     */
    public User searchForProfile(String username, String password) {
        loggedInUser = database.searchForUserInDatabase(username, password);
        return loggedInUser;
    }

    public void loadDataTypes() {
        database.loadDataTypesFromDatabase();
    }

    public void loadCityStates() {
        database.loadCityStatesFromDatabase();
    }

    public void loadPendingCityOfficials() {
        database.loadPendingCityOfficialsFromDatabase();
    }

    public void loadPendingDataPoints() {
        database.loadPendingDataPointsFromDatabase();
    }

    public void loadPOILocations() {
        database.loadPOILocationsFromDatabase();
    }

    public void acceptCityOfficialAccounts(ArrayList<CityOfficial> cityOfficials) {
        database.acceptCityOfficialAccountsIntoDatabase(cityOfficials);
    }

    public void rejectCityOfficialAccounts(ArrayList<CityOfficial> cityOfficials) {
        database.rejectCityOfficialAccountsIntoDatabase(cityOfficials);
    }

    public void acceptDataPoints(ArrayList<DataPoint> dataPoints) {
        database.acceptDataPointIntoDatabase(dataPoints);
    }

    public void rejectDataPoints(ArrayList<DataPoint> dataPoints) {
        database.rejectDataPointIntoDatabase(dataPoints);
    }

    public boolean checkCityStateCombo(String city, String state) {
        return database.checkCityStatesExist(city, state);
    }

    public void doQuery(String query) {
        database.doQueryInDatabase(query);
    }

    public void doDataPointQuery(String query) {
        database.doDataPointQueryInDatabase(query);
    }

    public void updatePOI(POI poi) {
        database.updatePOIInDatabase(poi);
    }
}
