package com.epam.spring.dao;

import com.epam.spring.config.AppConfig;
import com.epam.spring.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUserName("Andrey");
        user.setEmail("andrey@epam.com");
        user.setActive(true);
    }

    @Test
    public void testSaveUser() throws Exception {
        userRepository.save(user);

        User actualUser = userRepository.findByUserName("Andrey");

        assertEquals(user, actualUser);
    }
}