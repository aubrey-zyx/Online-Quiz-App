package com.bfs.logindemo.dao.jdbc;

import com.bfs.logindemo.dao.rowmapper.ChoiceRowMapper;
import com.bfs.logindemo.domain.jdbc.Choice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChoiceDao {
    private final JdbcTemplate jdbcTemplate;
    private final ChoiceRowMapper choiceRowMapper;

    public ChoiceDao(JdbcTemplate jdbcTemplate, ChoiceRowMapper choiceRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.choiceRowMapper = choiceRowMapper;
    }

    // Get all choices for a specific question
    public List<Choice> getChoicesForQuestion(int questionId) {
        String sql = "SELECT * FROM Choice WHERE question_id = ?";
        return jdbcTemplate.query(sql, choiceRowMapper, questionId);
    }
}

