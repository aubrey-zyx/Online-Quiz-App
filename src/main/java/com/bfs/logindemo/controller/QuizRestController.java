package com.bfs.logindemo.controller;

import com.bfs.logindemo.domain.Quiz;
import com.bfs.logindemo.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizRestController {
    private final QuizService quizService;

    public QuizRestController(QuizService quizService) {
        this.quizService = quizService;
    }

    // Get all quizzes done by a user
    @GetMapping
    public ResponseEntity<?> getQuizzesByUser(@RequestParam int userId) {
        List<Quiz> quizzes = quizService.getUserQuizzes(userId);

        if (quizzes.isEmpty()) {
            return ResponseEntity.badRequest().body("No quizzes found for this user.");
        }

        return ResponseEntity.ok(quizzes);
    }
}
