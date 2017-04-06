package model;

/**
 * represents a User Object
 */
public class User {

    private String username;
    private String password;
    private String email;

    /**
     * makes a user with a username, password, and email
     * @param username user username
     * @param password user password
     * @param email    user email
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
