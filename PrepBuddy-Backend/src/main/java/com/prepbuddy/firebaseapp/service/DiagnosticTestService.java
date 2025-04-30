// package com.prepbuddy.firebaseapp.service;

// import com.google.cloud.firestore.*;
// import com.google.firebase.cloud.FirestoreClient;
// import com.prepbuddy.firebaseapp.model.DiagnosticTestResult;
// import com.prepbuddy.firebaseapp.model.Question;
// import org.springframework.stereotype.Service;
// import java.util.*;
// import java.util.concurrent.ExecutionException;

// @Service
// public class DiagnosticTestService {
//     private final Firestore firestore;
//     private final QuestionService questionService;
//     private static final String COLLECTION_NAME = "diagnostic_test_results";
//     private static final int QUESTIONS_PER_LEVEL = 10;
//     private static final int TOTAL_SCORE = 100;
//     private static final int EASY_PENALTY = 3;
//     private static final int MEDIUM_PENALTY = 2;
//     private static final int HARD_PENALTY = 1;

//     public DiagnosticTestService(QuestionService questionService) {
//         this.firestore = FirestoreClient.getFirestore();
//         this.questionService = questionService;
//     }

//     public List<Question> generateDiagnosticTest(String subject) throws ExecutionException, InterruptedException {
//         List<Question> testQuestions = new ArrayList<>();
        
//         // Get questions for each difficulty level
//         testQuestions.addAll(questionService.getRandomQuestions(QUESTIONS_PER_LEVEL, subject, "EASY"));
//         testQuestions.addAll(questionService.getRandomQuestions(QUESTIONS_PER_LEVEL, subject, "MEDIUM"));
//         testQuestions.addAll(questionService.getRandomQuestions(QUESTIONS_PER_LEVEL, subject, "HARD"));
        
//         return testQuestions;
//     }

//     public DiagnosticTestResult evaluateTest(String userId, Map<String, List<String>> answers) 
//             throws ExecutionException, InterruptedException {
//         DiagnosticTestResult result = new DiagnosticTestResult();
//         result.setUserId(userId);
//         result.setAnswers(answers);
//         result.setCompletedAt(System.currentTimeMillis());
        
//         Map<String, Integer> scoresByDifficulty = new HashMap<>();
//         List<String> incorrectQuestions = new ArrayList<>();
//         int totalScore = TOTAL_SCORE;
        
//         // Evaluate each answer
//         for (Map.Entry<String, List<String>> entry : answers.entrySet()) {
//             String questionId = entry.getKey();
//             List<String> selectedAnswers = entry.getValue();
            
//             Question question = questionService.getQuestion(questionId);
//             if (question != null) {
//                 boolean isCorrect = selectedAnswers.equals(Arrays.asList(question.getCorrectAnswer()));
                
//                 if (!isCorrect) {
//                     incorrectQuestions.add(questionId);
//                     int penalty = switch (question.getDifficultyLevel()) {
//                         case "EASY" -> EASY_PENALTY;
//                         case "MEDIUM" -> MEDIUM_PENALTY;
//                         case "HARD" -> HARD_PENALTY;
//                         default -> 0;
//                     };
//                     totalScore -= penalty;
                    
//                     // Update scores by difficulty
//                     scoresByDifficulty.merge(question.getDifficultyLevel(), penalty, Integer::sum);
//                 }
//             }
//         }
        
//         result.setTotalScore(totalScore);
//         result.setScoresByDifficulty(scoresByDifficulty);
//         result.setIncorrectQuestions(incorrectQuestions);
//         result.setProgressionLevel(determineProgressionLevel(totalScore));
        
//         // Save the result
//         DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
//         result.setId(docRef.getId());
//         docRef.set(result).get();
        
//         return result;
//     }

//     private String determineProgressionLevel(int score) {
//         if (score >= 81) {
//             return "GOLD";
//         } else if (score >= 61) {
//             return "SILVER";
//         } else {
//             return "BRONZE";
//         }
//     }

//     public DiagnosticTestResult getTestResult(String userId) throws ExecutionException, InterruptedException {
//         Query query = firestore.collection(COLLECTION_NAME)
//             .whereEqualTo("userId", userId)
//             .orderBy("completedAt", Query.Direction.DESCENDING)
//             .limit(1);
            
//         QuerySnapshot querySnapshot = query.get().get();
//         if (!querySnapshot.isEmpty()) {
//             return querySnapshot.getDocuments().get(0).toObject(DiagnosticTestResult.class);
//         }
//         return null;
//     }
// } 