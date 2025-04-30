package com.prepbuddy.firebaseapp.controller;

public class UserLoginRequest {
    private String email;
    private String password;
    private String userType;

    // Getters and Setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType){
        this.userType = userType;
    }

    public String getUserType(){
        return userType;
    }
}
