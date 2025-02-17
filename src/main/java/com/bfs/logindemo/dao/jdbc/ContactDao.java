package com.bfs.logindemo.dao.jdbc;

import com.bfs.logindemo.domain.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDao {

    private final JdbcTemplate jdbcTemplate;

    public ContactDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Save a new contact message
    public void saveMessage(Contact contact) {
        String sql = "INSERT INTO Contact (subject, message, email, time) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, contact.getSubject(), contact.getMessage(), contact.getEmail(), contact.getTime());
    }

    // RowMapper to map SQL result to ContactMessage object
    private final RowMapper<Contact> contactMessageRowMapper = (rs, rowNum) -> new Contact(
            rs.getInt("contact_id"),
            rs.getString("subject"),
            rs.getString("message"),
            rs.getString("email"),
            rs.getTimestamp("time").toLocalDateTime()
    );
}
