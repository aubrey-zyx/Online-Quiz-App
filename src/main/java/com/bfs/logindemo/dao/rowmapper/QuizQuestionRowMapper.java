package com.bfs.logindemo.dao.rowmapper;

import com.bfs.logindemo.domain.jdbc.QuizQuestion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizQuestionRowMapper implements RowMapper<QuizQuestion> {
    @Override
    public QuizQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new QuizQuestion(
                rs.getInt("qq_id"),
                rs.getInt("quiz_id"),
                rs.getInt("question_id"),
                rs.getInt("user_choice_id")
        );
    }
}

