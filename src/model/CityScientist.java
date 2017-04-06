package model;

/**
 * represents a City Scientist Object
 */
public class CityScientist extends User {

    /**
     * makes a City Scientist with a username, password, and email
     * @param username City Scientist username
     * @param password City Scientist password
     * @param email    City Scientist email
     */
    public CityScientist(String username, String password, String email, String title) {
        super(username, password, email);
    }
}
