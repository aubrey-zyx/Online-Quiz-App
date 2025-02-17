package com.bfs.logindemo.dao.jdbc;

import com.bfs.logindemo.dao.rowmapper.QuizQuestionRowMapper;
import com.bfs.logindemo.domain.jdbc.QuizQuestion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizQuestionDao {
    private final JdbcTemplate jdbcTemplate;
    private final QuizQuestionRowMapper quizQuestionRowMapper;

    public QuizQuestionDao(JdbcTemplate jdbcTemplate, QuizQuestionRowMapper quizQuestionRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.quizQuestionRowMapper = quizQuestionRowMapper;
    }

    // Store selected questions for a quiz
    public void insertQuizQuestions(int quizId, List<Integer> questionIds) {
        if (quizId <= 0) {
            throw new IllegalArgumentException("Invalid quizId: " + quizId);
        }

        String sql = "INSERT INTO QuizQuestion (quiz_id, question_id) VALUES (?, ?)";

        for (int questionId : questionIds) {
            jdbcTemplate.update(sql, quizId, questionId);
        }
    }

    // Update user's selected choices for each question
    public void updateUserChoices(int quizId, int questionId, int userChoiceId) {
        String sql = "UPDATE QuizQuestion SET user_choice_id = ? WHERE quiz_id = ? AND question_id = ?";
        jdbcTemplate.update(sql, userChoiceId, quizId, questionId);
    }

    // Retrieve quiz questions for a given quiz
    public List<QuizQuestion> getQuizQuestions(int quizId) {
        String sql = "SELECT * FROM QuizQuestion WHERE quiz_id = ?";
        return jdbcTemplate.query(sql, quizQuestionRowMapper, quizId);
    }
}

