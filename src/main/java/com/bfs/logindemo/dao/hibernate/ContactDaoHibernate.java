package com.bfs.logindemo.dao.hibernate;

import com.bfs.logindemo.dao.AbstractHibernateDao;
import com.bfs.logindemo.domain.Contact;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ContactDaoHibernate extends AbstractHibernateDao<Contact> {

    public ContactDaoHibernate() {
        setClazz(Contact.class);
    }

    // Save a new contact message
    public void saveMessage(Contact contact) {
        add(contact);
    }

    public List<Contact> getAllMessages() {
        return getAll();
    }
}
