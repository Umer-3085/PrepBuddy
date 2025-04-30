package com.prepbuddy.firebaseapp.controller;

import org.springframework.web.bind.annotation.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody UserLoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        String userType;

        System.out.println("Here I am!");

        try {
            Firestore db = FirestoreClient.getFirestore();

            ApiFuture<QuerySnapshot> future = db.collection("students")
                    .whereEqualTo("email", email)
                    .limit(1)
                    .get();
             
            QuerySnapshot querySnapshot = future.get();

            System.out.println("Received login email: " + email);
            System.out.println("Users found: " + querySnapshot.size());

            if (!querySnapshot.isEmpty()) {
                DocumentSnapshot document = querySnapshot.getDocuments().get(0);
                String storedPassword = document.getString("password");

                if (storedPassword != null && storedPassword.equals(password)) {
                    userType = document.getString("userType");
                    return ResponseEntity.ok(new LoginResponse("Login successful", userType));
                } else {
                    return ResponseEntity.status(401).body(new LoginResponse("Incorrect password", null));
                }
            } else {
                return ResponseEntity.status(404).body(new LoginResponse("User not found", null));
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new LoginResponse("Error during login", null));
        }
    }
}
