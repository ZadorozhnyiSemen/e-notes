package com.epam.spring.service;

import com.epam.spring.model.User;

import java.util.List;

public interface UserService extends BaseEntityService<User> {

    List<User> findAllByUsername(String userName);

    List<User> getAll();

    User update(User user);

    User delete(Long id);
}
