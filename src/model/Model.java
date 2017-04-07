package model;

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
    }

    /**
     * add a user to the database
     * @param user the user to add to the database
     * @return true if user added, user if not added
     */
    public boolean addUser(User user) {
        if (database.addUser(user)) {
            loggedInUser = user;
            return true;
        }
        return false;
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

    public void loadPendingCityOfficials() {
        database.loadPendingCityOfficialsFromDatabase();
    }
}
