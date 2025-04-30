// Bookmark.java
package com.example.prepbuddy.backend;
import java.util.List;
public class Bookmark {
    private String StudentID;
    private String QuestionText;
    private List<String> Options;
    private int CorrectOption;

    // empty constructor
    public Bookmark() {}

    // getters and setters
    public String getStudentId() {
        return StudentID;
    }
    public void setStudentId(String studentId) {
        this.StudentID = studentId;
    }
    public String getQuestionText() {
        return QuestionText;
    }
    public void setQuestionText(String questionText) {
        this.QuestionText = questionText;
    }
    public List<String> getOptions() {
        return Options;
    }
    public void setOptions(List<String> options) {
        this.Options = options;
    }
    public int getCorrectOption() {
        return CorrectOption;
    }
    public void setCorrectOption(int correctOption) {
        this.CorrectOption = correctOption;
    }
}
