package model;

/**
 * represents an Admin Object
 */
public class Admin extends User {

    /**
     * makes an Admin with a username, password, and email
     * @param username Admin username
     * @param password Admin password
     * @param email    Admin email
     */
    public Admin(String username, String password, String email) {
        super(username, password, email);
    }
}
