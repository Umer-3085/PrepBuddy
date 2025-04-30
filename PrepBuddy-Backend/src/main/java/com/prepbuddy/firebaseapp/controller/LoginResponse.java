package com.prepbuddy.firebaseapp.controller;

public class LoginResponse {
    private String message;
    private String userType;

    public LoginResponse(String message, String userType) {
        this.message = message;
        this.userType = userType;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public String getUserType() {
        return userType;
    }
}
