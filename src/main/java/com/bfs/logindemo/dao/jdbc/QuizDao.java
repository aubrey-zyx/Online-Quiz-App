package com.bfs.logindemo.dao.jdbc;

import com.bfs.logindemo.dao.rowmapper.QuizRowMapper;
import com.bfs.logindemo.domain.jdbc.Quiz;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class QuizDao {
    private final JdbcTemplate jdbcTemplate;
    private final QuizRowMapper quizRowMapper;

    public QuizDao(JdbcTemplate jdbcTemplate, QuizRowMapper quizRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.quizRowMapper = quizRowMapper;
    }

    // Create a new quiz
    public int createQuiz(int userId, int categoryId, String quizName) {
        String sql = "INSERT INTO Quiz (user_id, category_id, name, time_start, score) VALUES (?, ?, ?, ?, ?)";

        LocalDateTime now = LocalDateTime.now();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setInt(2, categoryId);
            ps.setString(3, quizName);
            ps.setObject(4, now);
            ps.setInt(5, 0);
            return ps;
        }, keyHolder);

        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
    }

    // Update quiz score after submission
    public void updateQuizScore(int quizId, int score) {
        String sql = "UPDATE Quiz SET score = ?, time_end = ? WHERE quiz_id = ?";
        jdbcTemplate.update(sql, score, LocalDateTime.now(), quizId);
    }

    // Fetch past quizzes for a user
    public List<Quiz> getUserQuizzes(int userId) {
        String sql = "SELECT * FROM Quiz WHERE user_id = ? ORDER BY time_start DESC";
        return jdbcTemplate.query(sql, quizRowMapper, userId);
    }

    public Quiz getQuizById(int quizId) {
        String sql = "SELECT * FROM Quiz WHERE quiz_id = ?";
        return jdbcTemplate.queryForObject(sql, quizRowMapper, quizId);
    }

    public void updateQuizName(int quizId, String quizName) {
        String sql = "UPDATE Quiz SET name = ? WHERE quiz_id = ?";
        jdbcTemplate.update(sql, quizName, quizId);
    }
}


