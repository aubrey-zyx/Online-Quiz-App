package com.bfs.logindemo.dao.hibernate;

import com.bfs.logindemo.dao.AbstractHibernateDao;
import com.bfs.logindemo.domain.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("userDaoHibernate")
@Transactional
public class UserDaoHibernate extends AbstractHibernateDao<User> {
    public UserDaoHibernate() {
        setClazz(User.class);
    }

    public List<User> getAllUsers() {
        return getAll();
    }

    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(findById(id));
    }

    public Optional<User> getUserByEmail(String email) {
        Session session = getCurrentSession();
        return session.createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", email)
                .uniqueResultOptional();
    }

    public void createNewUser(User user) {
        add(user);
    }

    public void deleteUser(int userId) {
        Session session = getCurrentSession();
        User user = session.get(User.class, userId);
        if (user != null) {
            session.delete(user);
        }
    }

    public void updateUserStatus(int userId, boolean isActive) {
        Session session = getCurrentSession();
        User user = session.get(User.class, userId);
        if (user != null) {
            user.setActive(isActive);
            session.update(user);
        }
    }
}
