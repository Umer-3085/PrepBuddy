package com.example.firebase_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Question") // Explicitly map to the "Question" table
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Changed to Integer to match IDENTITY column which can be null before saving

    @Column(columnDefinition = "TEXT") // Map to TEXT type
    private String answer;

    @Column(columnDefinition = "TEXT")
    private String difficulty;

    @Column(columnDefinition = "TEXT")
    private String option1;

    @Column(columnDefinition = "TEXT")
    private String option2;

    @Column(columnDefinition = "TEXT")
    private String option3;

    @Column(columnDefinition = "TEXT")
    private String option4;

    @Column(columnDefinition = "TEXT")
    private String question; // Field name matches table column

    @Column(columnDefinition = "TEXT")
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String topic;
}