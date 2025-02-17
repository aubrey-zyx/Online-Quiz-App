package com.bfs.logindemo.dao.hibernate;

import com.bfs.logindemo.dao.AbstractHibernateDao;
import com.bfs.logindemo.domain.Choice;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ChoiceDaoHibernate extends AbstractHibernateDao<Choice> {

    public ChoiceDaoHibernate() {
        setClazz(Choice.class);
    }

    // Get all choices for a specific question
    public List<Choice> getChoicesForQuestion(int questionId) {
        Session session = getCurrentSession();
        TypedQuery<Choice> query = session.createQuery(
                "FROM Choice WHERE question.id = :questionId",
                Choice.class
        );
        query.setParameter("questionId", questionId);
        return query.getResultList();
    }

    public void deleteChoicesByQuestionId(int questionId) {
        Session session = getCurrentSession();
        session.createQuery("DELETE FROM Choice WHERE question.questionId = :questionId")
                .setParameter("questionId", questionId)
                .executeUpdate();
    }
}
