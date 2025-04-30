package com.prepbuddy.firebaseapp.model;

public class User {
    private String username;
    private String password;
    private String email;

    public boolean login(String username, String password) {
        // Implement login logic
        return true;
    }

    public void changePassword(String newPassword) {
        // Implement password change logic
        this.password = newPassword;
    }

    // Getters and Setters
    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

