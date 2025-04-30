package com.prepbuddy.firebaseapp.model;

import java.util.Map;

public class Question {
    
    private String statement;
    private Map<String,String> options;
    private String correctAnswer;
    private String studentAnswer;
    private String difficulty;
    private String subject;
    private String topic;

    // Constructor
    public Question(int questionID, String statement, Map<String,String> options, String correctAnswer, String difficulty) {
        this.statement = statement;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.studentAnswer = null;
    }

    // Getters and Setters
    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Map<String,String> getOptions() {
        return options;
    }

    public void setOptions(Map<String,String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
