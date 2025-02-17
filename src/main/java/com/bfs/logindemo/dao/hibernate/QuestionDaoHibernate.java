package com.bfs.logindemo.dao.hibernate;

import com.bfs.logindemo.dao.AbstractHibernateDao;
import com.bfs.logindemo.domain.Question;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class QuestionDaoHibernate extends AbstractHibernateDao<Question> {

    public QuestionDaoHibernate() {
        setClazz(Question.class);
    }

    // Fetch 5 random questions for a given category
    public List<Question> getRandomQuestions(int categoryId) {
        Session session = getCurrentSession();
        TypedQuery<Question> query = session.createQuery(
                "FROM Question WHERE category.id = :categoryId ORDER BY function('RAND')",
                Question.class
        );
        query.setParameter("categoryId", categoryId);
        query.setMaxResults(5);
        return query.getResultList();
    }

    public Question getQuestionById(int questionId) {
        return findById(questionId);
    }

    public List<Question> getAllQuestions() {
        return getAll();
    }

    public void updateQuestionStatus(int questionId, boolean isActive) {
        Session session = getCurrentSession();
        Question question = session.get(Question.class, questionId);
        if (question != null) {
            question.setActive(isActive);
            session.update(question);
        }
    }
}
