package com.example.firebase_app.controller;

import com.example.firebase_app.entity.Question;
import com.example.firebase_app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody Question questionDto) {
        // Map DTO to Entity
        Question question = new Question();
        question.setAnswer(questionDto.getAnswer());
        question.setDifficulty(questionDto.getDifficulty());
        question.setOption1(questionDto.getOption1());
        question.setOption2(questionDto.getOption2());
        question.setOption3(questionDto.getOption3());
        question.setOption4(questionDto.getOption4());
        question.setQuestion(questionDto.getQuestion());
        question.setSubject(questionDto.getSubject());
        question.setTopic(questionDto.getTopic());

        // Save the question to the database
        Question savedQuestion = questionRepository.save(question);

        System.out.println("Question saved: " + savedQuestion.getId());

        // Return the saved question (or just a success message)
        return ResponseEntity.ok(savedQuestion);
    }
}