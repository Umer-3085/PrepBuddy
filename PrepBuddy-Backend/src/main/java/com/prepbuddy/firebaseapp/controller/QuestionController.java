package com.prepbuddy.firebaseapp.controller;

import org.springframework.web.bind.annotation.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.ExecutionException;
import com.prepbuddy.firebaseapp.model.Question;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/questions")
public class QuestionController {

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference questionsCollection = db.collection("Questions");

            Map<String, Object> questionData = new HashMap<>();
            questionData.put("Statement", question.getStatement());
            questionData.put("Options", question.getOptions());
            questionData.put("Answer", question.getCorrectAnswer());
            questionData.put("Difficulty", question.getDifficulty());
            questionData.put("Subject", question.getSubject());
            questionData.put("Topic", question.getTopic());

            ApiFuture<DocumentReference> future = questionsCollection.add(questionData);
            DocumentReference docRef = future.get();

            return ResponseEntity.ok("Question added successfully with ID: " + docRef.getId());

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error adding question to Firestore");
        }
    }
}
