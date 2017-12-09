package com.epam.spring;

import com.epam.spring.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    @Autowired
    UserRepository repository;

    public static void main(String[] args) {

    }
}
