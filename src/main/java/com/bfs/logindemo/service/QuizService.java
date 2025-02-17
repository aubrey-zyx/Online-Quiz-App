package com.bfs.logindemo.service;

import com.bfs.logindemo.dao.hibernate.CategoryDaoHibernate;
import com.bfs.logindemo.dao.hibernate.QuizDaoHibernate;
import com.bfs.logindemo.dao.hibernate.UserDaoHibernate;
import com.bfs.logindemo.dao.jdbc.QuizDao;
import com.bfs.logindemo.domain.Category;
import com.bfs.logindemo.domain.Quiz;
import com.bfs.logindemo.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizDaoHibernate quizDao;
    private final UserDaoHibernate userDao;
    private final CategoryDaoHibernate categoryDao;

    public QuizService(QuizDaoHibernate quizDao, UserDaoHibernate userDao, CategoryDaoHibernate categoryDao) {
        this.quizDao = quizDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
    }

    public int createQuiz(int userId, int categoryId, String quizName) {
        // Fetch the User and Category entities
        User user = userDao.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Category category = categoryDao.getCategoryById(categoryId);

        // Create a new Quiz entity
        Quiz quiz = new Quiz();
        quiz.setUser(user);
        quiz.setCategory(category);
        quiz.setName(quizName);
        quiz.setTimeStart(LocalDateTime.now());
        quiz.setScore(0);

        // Save and return the generated Quiz ID
        return quizDao.createQuiz(quiz);
    }

    public void updateQuizScore(int quizId, int score) {
        quizDao.updateQuizScore(quizId, score);
    }

    public List<Quiz> getUserQuizzes(int userId) {
        return quizDao.getUserQuizzes(userId);
    }

    public Quiz getQuizById(int quizId) {
        return quizDao.getQuizById(quizId);
    }

    public void updateQuizName(int quizId, String quizName) {
        quizDao.updateQuizName(quizId, quizName);
    }

    public Optional<Quiz> getOngoingQuizByUser(int userId) {
        return quizDao.findOngoingQuizByUser(userId);
    }

    public List<Quiz> getFilteredQuizzes(Integer categoryId, Integer userId) {
        return quizDao.findFilteredQuizzes(categoryId, userId);
    }
}

