package com.bfs.logindemo.dao.rowmapper;

import com.bfs.logindemo.domain.jdbc.Choice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChoiceRowMapper implements RowMapper<Choice> {
    @Override
    public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Choice(
                rs.getInt("choice_id"),
                rs.getInt("question_id"),
                rs.getString("description"),
                rs.getBoolean("is_correct")
        );
    }
}

