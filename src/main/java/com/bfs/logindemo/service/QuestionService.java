package com.bfs.logindemo.service;

import com.bfs.logindemo.dao.hibernate.CategoryDaoHibernate;
import com.bfs.logindemo.dao.hibernate.ChoiceDaoHibernate;
import com.bfs.logindemo.dao.hibernate.QuestionDaoHibernate;
import com.bfs.logindemo.dao.jdbc.QuestionDao;
import com.bfs.logindemo.domain.Category;
import com.bfs.logindemo.domain.Choice;
import com.bfs.logindemo.domain.Question;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QuestionService {
    private final QuestionDaoHibernate questionDao;
    private final CategoryDaoHibernate categoryDao;
    private final ChoiceDaoHibernate choiceDao;

    public QuestionService(QuestionDaoHibernate questionDao, CategoryDaoHibernate categoryDao, ChoiceDaoHibernate choiceDao) {
        this.questionDao = questionDao;
        this.categoryDao = categoryDao;
        this.choiceDao = choiceDao;
    }

    public List<Question> getRandomQuestions(int categoryId) {
        return questionDao.getRandomQuestions(categoryId);
    }

    public Question getQuestionById(int questionId) {
        return questionDao.getQuestionById(questionId);
    }

    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    public void toggleQuestionStatus(int questionId) {
        Question question = questionDao.getQuestionById(questionId);
        questionDao.updateQuestionStatus(questionId, !question.isActive());
    }

    public void addQuestion(String description, int categoryId, List<String> choices, int correctChoiceIndex) {
        Category category = categoryDao.getCategoryById(categoryId);

        // Create new question
        Question question = new Question();
        question.setCategory(category);
        question.setDescription(description);
        question.setActive(true);

        questionDao.add(question);

        // Add choices for the question
        for (int i = 0; i < choices.size(); i++) {
            Choice choice = new Choice();
            choice.setQuestion(question);
            choice.setDescription(choices.get(i));
            choice.setCorrect(i == correctChoiceIndex);
            choiceDao.add(choice);
        }
    }

    public void updateQuestion(int questionId, String description, int categoryId, List<String> choices, int correctChoiceIndex) {
        Question question = questionDao.getQuestionById(questionId);
        if (question == null) {
            throw new RuntimeException("Question not found");
        }

        Category category = categoryDao.getCategoryById(categoryId);
        question.setCategory(category);
        question.setDescription(description);
        questionDao.update(question);

        // Remove old choices and add new ones
        choiceDao.deleteChoicesByQuestionId(questionId);
        for (int i = 0; i < choices.size(); i++) {
            Choice choice = new Choice();
            choice.setQuestion(question);
            choice.setDescription(choices.get(i));
            choice.setCorrect(i == correctChoiceIndex);
            choiceDao.add(choice);
        }
    }
}

