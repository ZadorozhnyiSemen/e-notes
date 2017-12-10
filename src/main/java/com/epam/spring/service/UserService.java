package com.epam.spring.service;

import com.epam.spring.dao.UserRepository;
import com.epam.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUserNameAndPassword(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, password);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }
}
