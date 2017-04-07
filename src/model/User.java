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

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getUser_type() {
        return user_type.get();
    }

    public SimpleStringProperty user_typeProperty() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type.set(user_type);
    }
}
