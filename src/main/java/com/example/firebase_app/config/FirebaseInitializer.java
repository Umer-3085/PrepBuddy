package com.example.firebase_app.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.io.IOException;

@Configuration
public class FirebaseInitializer {

    @PostConstruct
    public void init() {
        try {
            InputStream serviceAccount = getClass().getClassLoader()
                .getResourceAsStream("firebase-service-account.json");  

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            // Initialize Firebase if not already done
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            System.out.println("Firebase Initialized");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Initializing Firebase: " + e.getMessage());
        }
    }
}
