package com.bfs.logindemo.service;

import com.bfs.logindemo.dao.hibernate.ContactDaoHibernate;
import com.bfs.logindemo.dao.jdbc.ContactDao;
import com.bfs.logindemo.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactService {

    private final ContactDaoHibernate contactDao;

    @Autowired
    public ContactService(ContactDaoHibernate contactDao) {
        this.contactDao = contactDao;
    }

    // Save a contact message
    public boolean sendMessage(String subject, String email, String message) {
        Contact contact = new Contact(0, subject, message, email, LocalDateTime.now());
        contactDao.saveMessage(contact);
        return true;
    }

    public List<Contact> getAllMessages() {
        return contactDao.getAllMessages();
    }
}
