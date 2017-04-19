package model;

import javafx.beans.property.SimpleStringProperty;

/**
 * represents a User Object
 */
public class User {

    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleStringProperty email;
    private final SimpleStringProperty user_type;

    /**
     * makes a user with a username, password, email, and user type
     * @param username user username
     * @param password user password
     * @param email    user email
     */
    public User(String username, String password, String email, String user_type) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.email = new SimpleStringProperty(email);
        this.user_type = new SimpleStringProperty(user_type);
    }

    public String getUsername() {
        return username.get();
    }

    public String getPassword() {
        return password.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getUser_type() {
        return user_type.get();
    }
}
