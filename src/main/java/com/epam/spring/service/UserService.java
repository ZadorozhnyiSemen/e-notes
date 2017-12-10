package com.epam.spring.service;

import com.epam.spring.model.User;

import java.util.List;

public interface UserService extends BaseEntityService<User> {
    User findByUserNameAndPassword(String userName, String password);

    List<User> getAll();
}
