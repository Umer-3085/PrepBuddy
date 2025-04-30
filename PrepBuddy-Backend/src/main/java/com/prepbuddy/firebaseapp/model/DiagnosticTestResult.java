package com.prepbuddy.firebaseapp.model;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class DiagnosticTestResult {
    private String id;
    private String userId;
    private int totalScore;
    private String progressionLevel; // BRONZE, SILVER, GOLD
    private Map<String, Integer> scoresByDifficulty; // EASY, MEDIUM, HARD
    private List<String> incorrectQuestions;
    private long completedAt;
    private Map<String, List<String>> answers; // questionId -> selected answer
} 