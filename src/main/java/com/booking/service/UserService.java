package com.booking.service;

import com.booking.dao.UserDao;
import com.booking.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public User getUserById(long id) {
        logger.info("get user by id: {}", id );
        return userDao.getById(id);
    }

    public User getUserByEmail(String email) {
        logger.info("get user by email: {}", email );
        return userDao.findByByEmail(email);
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        logger.info("get users by name: {}", name );
        return userDao.findByName(name);
    }

    public User createUser(User user) {
        logger.info("create user: {}", user.getId() );
        return userDao.save(user);
    }

    public User updateUser(User user) {
        logger.info("update user: {}", user.getId() );
        return userDao.save(user);
    }

    public boolean deleteUser(long userId) {
        logger.info("delete user: {}", userId );
        userDao.deleteById(userId);
        return true;
    }
}