package com.bfs.logindemo.service;

import com.bfs.logindemo.dao.hibernate.ChoiceDaoHibernate;
import com.bfs.logindemo.dao.hibernate.QuestionDaoHibernate;
import com.bfs.logindemo.dao.hibernate.QuizDaoHibernate;
import com.bfs.logindemo.dao.hibernate.QuizQuestionDaoHibernate;
import com.bfs.logindemo.domain.Choice;
import com.bfs.logindemo.domain.Question;
import com.bfs.logindemo.domain.Quiz;
import com.bfs.logindemo.domain.QuizQuestion;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizQuestionService {

    private final QuizQuestionDaoHibernate quizQuestionDao;
    private final QuizDaoHibernate quizDao;
    private final QuestionDaoHibernate questionDao;
    private final ChoiceDaoHibernate choiceDao;

    public QuizQuestionService(
            QuizQuestionDaoHibernate quizQuestionDao,
            QuizDaoHibernate quizDao,
            QuestionDaoHibernate questionDao,
            ChoiceDaoHibernate choiceDao) {
        this.quizQuestionDao = quizQuestionDao;
        this.quizDao = quizDao;
        this.questionDao = questionDao;
        this.choiceDao = choiceDao;
    }

    // Store selected questions for a quiz
    @Transactional
    public void insertQuizQuestions(int quizId, List<Integer> questionIds) {
        Quiz quiz = quizDao.getQuizById(quizId);
        List<Question> questions = questionIds.stream()
                .map(questionDao::getQuestionById)
                .collect(Collectors.toList());

        quizQuestionDao.insertQuizQuestions(quiz, questions);
    }

    // Update user's selected choices for each question
    @Transactional
    public void updateUserChoices(int quizId, int questionId, int userChoiceId) {
        Quiz quiz = quizDao.getQuizById(quizId);
        Question question = questionDao.getQuestionById(questionId);
        Optional<Choice> userChoice = Optional.ofNullable(choiceDao.findById(userChoiceId));

        if (userChoice.isPresent()) {
            quizQuestionDao.updateUserChoices(quiz, question, userChoice.get());
        } else {
            throw new RuntimeException("Invalid choice ID: " + userChoiceId);
        }
    }

    // Retrieve all quiz questions for a given quiz
    @Transactional
    public List<QuizQuestion> getQuizQuestions(int quizId) {
        Quiz quiz = quizDao.getQuizById(quizId);
        return quizQuestionDao.getQuizQuestions(quiz);
    }
}


