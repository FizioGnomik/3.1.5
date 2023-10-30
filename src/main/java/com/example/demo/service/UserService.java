package com.example.demo.service;

import com.example.demo.model.User;
import javax.validation.Valid;
import java.util.List;

public interface UserService {
    void saveUser(User user);

    void updateUser(@Valid User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    User getUserByLogin(String login);

    User getUserById(long id);
}
