package com.bfs.logindemo.dao.rowmapper;

import com.bfs.logindemo.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getInt("user_id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getBoolean("is_active"),
                rs.getBoolean("is_admin")
        );
    }
}
