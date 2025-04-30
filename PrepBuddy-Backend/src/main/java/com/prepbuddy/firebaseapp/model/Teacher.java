package com.prepbuddy.firebaseapp.model;

public class Teacher extends User {
    private String subject;

    // Constructor
    public Teacher(String username, String password, String email, String subject) {
        setUserName(username);
        setPassword(password);
        setEmail(email);
        this.subject = subject;
    }
    
    @Override
    public void changePassword(String newPassword) {
        // Optional: add admin-specific password change rules
        super.changePassword(newPassword);
    }
    
    // Getters and Setters
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
