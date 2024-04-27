package com.project.usermanagement.service;


import com.project.usermanagement.pojo.User;
import com.project.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Integer createUser(User user) {
        return userRepository.createUser(user);
    }

    public Integer updateUser(User user) {
        return userRepository.updateUser(user);
    }

    public Integer deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    public List<User> listUsers() {
        return userRepository.listUsers();
    }


    public User login(String email, String password) {
        return userRepository.login(email, password);
    }
}
