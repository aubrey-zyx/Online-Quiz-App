package com.bfs.logindemo.dao.rowmapper;

import com.bfs.logindemo.domain.jdbc.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Question(
                rs.getInt("question_id"),
                rs.getInt("category_id"),
                rs.getString("description"),
                rs.getBoolean("is_active")
        );
    }
}

