package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dao.UserDao;
import com.example.demo.model.User;


import javax.validation.Valid;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(@Valid User user) {
        if (!user.getPassword().equals(userDao.getUserById(user.getUserId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }
}