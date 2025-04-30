package com.prepbuddy.firebaseapp.controller;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test-firebase")
    public String testFirebaseConnection() {
        try {
            DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("connectionTest");

            ref.setValueAsync("Firebase is working!");

            return "Write operation sent to Firebase DB.";
        } catch (Exception e) {
            return "Firebase connection failed: " + e.getMessage();
        }
    }
}
