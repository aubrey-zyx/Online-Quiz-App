package com.bfs.logindemo.dao.hibernate;

import com.bfs.logindemo.dao.AbstractHibernateDao;
import com.bfs.logindemo.domain.Category;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryDaoHibernate extends AbstractHibernateDao<Category> {

    public CategoryDaoHibernate() {
        setClazz(Category.class);
    }

    // Retrieve all categories from the database
    public List<Category> getAllCategories() {
        return getAll();
    }

    // Add a new category
    public void createNewCategory(Category category) {
        add(category);
    }

    public Category getCategoryById(int categoryId) {
        return findById(categoryId);
    }
}
