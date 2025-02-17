package com.bfs.logindemo.service;

import com.bfs.logindemo.dao.hibernate.CategoryDaoHibernate;
import com.bfs.logindemo.dao.jdbc.CategoryDao;
import com.bfs.logindemo.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryDaoHibernate categoryDao;

    @Autowired
    public CategoryService(CategoryDaoHibernate categoryDao) {
        this.categoryDao = categoryDao;
    }

    // Create a new category
    public void createNewCategory(Category category) {
        categoryDao.createNewCategory(category);
    }

    // Retrieve all categories
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }
}
