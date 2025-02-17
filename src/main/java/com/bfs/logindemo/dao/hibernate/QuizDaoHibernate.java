package com.bfs.logindemo.dao.hibernate;

import com.bfs.logindemo.dao.AbstractHibernateDao;
import com.bfs.logindemo.domain.Quiz;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class QuizDaoHibernate extends AbstractHibernateDao<Quiz> {

    public QuizDaoHibernate() {
        setClazz(Quiz.class);
    }

    // Create a new quiz
    public int createQuiz(Quiz quiz) {
        quiz.setTimeStart(LocalDateTime.now());
        quiz.setScore(0);
        add(quiz);
        return quiz.getQuizId(); // Hibernate automatically sets the ID after persisting
    }

    // Update quiz score after submission
    public void updateQuizScore(int quizId, int score) {
        Session session = getCurrentSession();
        Quiz quiz = session.get(Quiz.class, quizId);
        if (quiz != null) {
            quiz.setScore(score);
            quiz.setTimeEnd(LocalDateTime.now());
            session.update(quiz);
        }
    }

    // Fetch past quizzes for a user
    public List<Quiz> getUserQuizzes(int userId) {
        Session session = getCurrentSession();
        TypedQuery<Quiz> query = session.createQuery(
                "FROM Quiz WHERE user.id = :userId ORDER BY timeStart DESC",
                Quiz.class
        );
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    // Get a quiz by ID
    public Quiz getQuizById(int quizId) {
        return findById(quizId);
    }

    // Update quiz name
    public void updateQuizName(int quizId, String quizName) {
        Session session = getCurrentSession();
        Quiz quiz = session.get(Quiz.class, quizId);
        if (quiz != null) {
            quiz.setName(quizName);
            session.update(quiz);
        }
    }

    public Optional<Quiz> findOngoingQuizByUser(int userId) {
        Session session = getCurrentSession();
        TypedQuery<Quiz> query = session.createQuery(
                "FROM Quiz WHERE user.id = :userId AND timeEnd IS NULL",
                Quiz.class
        );
        query.setParameter("userId", userId);
        return query.getResultList().stream().findFirst();
    }

    public List<Quiz> findFilteredQuizzes(Integer categoryId, Integer userId) {
        Session session = getCurrentSession();
        String hql = "FROM Quiz q WHERE 1=1";

        if (categoryId != null) {
            hql += " AND q.category.id = :categoryId";
        }
        if (userId != null) {
            hql += " AND q.user.id = :userId";
        }
        hql += " ORDER BY q.timeEnd DESC";

        TypedQuery<Quiz> query = session.createQuery(hql, Quiz.class);
        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }
        if (userId != null) {
            query.setParameter("userId", userId);
        }

        return query.getResultList();
    }
}
