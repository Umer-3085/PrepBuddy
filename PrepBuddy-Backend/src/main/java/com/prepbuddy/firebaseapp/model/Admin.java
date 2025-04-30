package com.prepbuddy.firebaseapp.model;

public class Admin extends User {

    // Constructor
    public Admin(String username, String password, String email) {
        setUserName(username);
        setPassword(password);
        setEmail(email);
    }

    // Admin-specific methods
    public String addQuestion() {
        // Implement logic
        return "Question added.";
    }

    public String removeQuestion() {
        // Implement logic
        return "Question removed.";
    }


    @Override
    public void changePassword(String newPassword) {
        // Optional: add admin-specific password change rules
        super.changePassword(newPassword);
    }

    public String addTeacher() {
        // Implement logic
        return "Teacher added.";
    }

    public String removeTeacher() {
        // Implement logic
        return "Teacher removed.";
    }

    public String removeStudent() {
        // Implement logic
        return "Student removed.";
    }
}
