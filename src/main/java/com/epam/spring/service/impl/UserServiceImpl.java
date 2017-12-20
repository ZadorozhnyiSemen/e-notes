package com.epam.spring.service.impl;

import com.epam.spring.dao.UserRepository;
import com.epam.spring.model.User;
import com.epam.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllByUsername(String userName) {
        List<User> users = userRepository.findByUserName(userName);
        if (users.size() == 0) {
            List<User> searchUsers = userRepository.findAll().stream()
                    .filter(user -> user.getUserName().contains(userName))
                    .collect(Collectors.toList());
            return searchUsers;
        } else {
            return users;
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public int update(Long id, User user) {
        return userRepository.update(user.getUserName(), user.getPassword(), user.getEmail(), id);
    }

    public User delete(Long id) {
        User updateUserActivity = userRepository.getById(id);
        if (updateUserActivity.isActive()) {
            userRepository.delete(id);
        }
        return updateUserActivity;
    }
}
