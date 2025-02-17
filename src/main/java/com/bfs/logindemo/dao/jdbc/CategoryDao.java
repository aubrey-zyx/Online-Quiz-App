package com.bfs.logindemo.dao.jdbc;

import com.bfs.logindemo.dao.rowmapper.CategoryRowMapper;
import com.bfs.logindemo.domain.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {

    private final JdbcTemplate jdbcTemplate;
    private final CategoryRowMapper categoryRowMapper;

    public CategoryDao(JdbcTemplate jdbcTemplate, CategoryRowMapper categoryRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryRowMapper = categoryRowMapper;
    }

    // Retrieve all categories from the database
    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM Category";
        return jdbcTemplate.query(sql, categoryRowMapper);
    }

    // Add a new category
    public void createNewCategory(Category category) {
        String sql = "INSERT INTO Category (name) VALUES (?)";
        jdbcTemplate.update(sql, category.getName());
    }
}
