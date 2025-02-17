package com.bfs.logindemo.dao.jdbc;

import com.bfs.logindemo.dao.rowmapper.QuestionRowMapper;
import com.bfs.logindemo.domain.jdbc.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDao {
    private final JdbcTemplate jdbcTemplate;
    private final QuestionRowMapper questionRowMapper;

    public QuestionDao(JdbcTemplate jdbcTemplate, QuestionRowMapper questionRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.questionRowMapper = questionRowMapper;
    }

    // Fetch 5 random questions for a given category
    public List<Question> getRandomQuestions(int categoryId) {
        String sql = "SELECT * FROM Question WHERE category_id = ? ORDER BY RAND() LIMIT 5";
        return jdbcTemplate.query(sql, questionRowMapper, categoryId);
    }

    public Question getQuestionById(int questionId) {
        String sql = "SELECT * FROM Question WHERE question_id = ?";
        return jdbcTemplate.queryForObject(sql, questionRowMapper, questionId);
    }
}

