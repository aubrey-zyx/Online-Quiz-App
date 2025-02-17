package com.bfs.logindemo.service;

import com.bfs.logindemo.dao.hibernate.ChoiceDaoHibernate;
import com.bfs.logindemo.dao.jdbc.ChoiceDao;
import com.bfs.logindemo.domain.Choice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {
    private final ChoiceDaoHibernate choiceDao;

    public ChoiceService(ChoiceDaoHibernate choiceDao) {
        this.choiceDao = choiceDao;
    }

    public List<Choice> getChoicesForQuestion(int questionId) {
        return choiceDao.getChoicesForQuestion(questionId);
    }
}
