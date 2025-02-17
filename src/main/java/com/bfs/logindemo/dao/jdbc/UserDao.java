package com.bfs.logindemo.dao.jdbc;

import com.bfs.logindemo.dao.rowmapper.UserRowMapper;
import com.bfs.logindemo.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserDao(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    // Get all users from the database
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM User";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    // Get a user by ID
    public Optional<User> getUserById(int id) {
        String sql = "SELECT * FROM User WHERE user_id = ?";
        return jdbcTemplate.query(sql, userRowMapper, id)
                .stream()
                .findFirst();
    }

    // Get a user by Email
    public Optional<User> getUserByEmail(String email) {
        String sql = "SELECT * FROM User WHERE email = ?";
        return jdbcTemplate.query(sql, userRowMapper, email)
                .stream()
                .findFirst();
    }

    // Insert a new user
    public void createNewUser(User user) {
        String sql = "INSERT INTO User (email, password, firstname, lastname, is_active, is_admin) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.isActive(), user.isAdmin());
    }

    public void deleteUser(int userId) {
        String sql = "DELETE FROM User WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    public void updateUserStatus(int userId, boolean isActive) {
        String sql = "UPDATE User SET is_active = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, isActive, userId);
    }
}
