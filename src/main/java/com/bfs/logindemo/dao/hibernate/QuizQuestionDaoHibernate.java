package com.bfs.logindemo.dao.hibernate;

import com.bfs.logindemo.dao.AbstractHibernateDao;
import com.bfs.logindemo.domain.Choice;
import com.bfs.logindemo.domain.Question;
import com.bfs.logindemo.domain.Quiz;
import com.bfs.logindemo.domain.QuizQuestion;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class QuizQuestionDaoHibernate extends AbstractHibernateDao<QuizQuestion> {

    public QuizQuestionDaoHibernate() {
        setClazz(QuizQuestion.class);
    }

    // Store selected questions for a quiz
    public void insertQuizQuestions(Quiz quiz, List<Question> questions) {
        if (quiz == null || questions == null || questions.isEmpty()) {
            throw new IllegalArgumentException("Invalid quiz or question list");
        }

        Session session = getCurrentSession();
        for (Question question : questions) {
            QuizQuestion quizQuestion = new QuizQuestion();
            quizQuestion.setQuiz(quiz);
            quizQuestion.setQuestion(question);
            session.save(quizQuestion);
        }
    }

    // Update user's selected choices for each question
    public void updateUserChoices(Quiz quiz, Question question, Choice userChoice) {
        Session session = getCurrentSession();
        TypedQuery<QuizQuestion> query = session.createQuery(
                "FROM QuizQuestion WHERE quiz = :quiz AND question = :question",
                QuizQuestion.class
        );
        query.setParameter("quiz", quiz);
        query.setParameter("question", question);

        QuizQuestion quizQuestion = query.getSingleResult();
        quizQuestion.setUserChoice(userChoice);
        session.update(quizQuestion);
    }

    // Retrieve quiz questions for a given quiz
    public List<QuizQuestion> getQuizQuestions(Quiz quiz) {
        Session session = getCurrentSession();
        TypedQuery<QuizQuestion> query = session.createQuery(
                "FROM QuizQuestion WHERE quiz = :quiz",
                QuizQuestion.class
        );
        query.setParameter("quiz", quiz);
        return query.getResultList();
    }
}
