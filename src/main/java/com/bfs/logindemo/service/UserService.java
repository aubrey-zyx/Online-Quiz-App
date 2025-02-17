package com.bfs.logindemo.service;

import com.bfs.logindemo.dao.hibernate.UserDaoHibernate;
import com.bfs.logindemo.domain.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDaoHibernate userDao;

    public UserService(UserDaoHibernate userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void createNewUser(User user) {
        userDao.createNewUser(user);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public Optional<User> getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public Optional<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Transactional
    public Optional<User> validateLogin(String email, String password) {
        return userDao.getUserByEmail(email)
                .filter(user -> user.getPassword().equals(password));
    }

    @Transactional
    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }

    @Transactional
    public void updateUserStatus(int userId, boolean isActive) {
        userDao.updateUserStatus(userId, isActive);
    }
}

//@Service
//public class UserService {
//    private final UserDao userDao;
//
//    public UserService(UserDao userDao) {
//        this.userDao = userDao;
//    }
//
//    public void createNewUser(User user) {
//        userDao.createNewUser(user);
//    }
//
//    public List<User> getAllUsers() {
//        return userDao.getAllUsers();
//    }
//
//    public Optional<User> getUserById(int id) {
//        return userDao.getUserById(id);
//    }
//
//    public Optional<User> getUserByEmail(String email) {
//        return userDao.getUserByEmail(email);
//    }
//
//    public Optional<User> validateLogin(String email, String password) {
//        return userDao.getUserByEmail(email)
//                .filter(user -> user.getPassword().equals(password));
//    }
//
//    public void deleteUser(int userId) {
//        userDao.deleteUser(userId);
//    }
//
//    public void updateUserStatus(int userId, boolean isActive) {
//        userDao.updateUserStatus(userId, isActive);
//    }
//}
