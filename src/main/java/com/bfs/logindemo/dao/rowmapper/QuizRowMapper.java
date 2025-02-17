package com.bfs.logindemo.dao.rowmapper;

import com.bfs.logindemo.domain.jdbc.Quiz;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizRowMapper implements RowMapper<Quiz> {
    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Quiz(
                rs.getInt("quiz_id"),
                rs.getInt("user_id"),
                rs.getInt("category_id"),
                rs.getString("name"),
                rs.getTimestamp("time_start").toLocalDateTime(),
                rs.getTimestamp("time_end") != null ? rs.getTimestamp("time_end").toLocalDateTime() : null,
                rs.getInt("score")
        );
    }
}

