package com.epam.spring.dao;

import com.epam.spring.config.AppConfig;
import com.epam.spring.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = AppConfig.class)
public class UserRepositoryTest {

    private static final String TAG_NAME = "Awesome tag";

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        // watch resources/update.sql
    }

    @Test
    public void testFindUserByUsername() throws Exception {
        List<User> actual = userRepository.findByUserName("Semen");
        assertEquals(1, actual.size());
        assertEquals("Semen", actual.get(0).getUserName());
    }

    @Test
    public void testGetById() throws Exception {
        User actual = userRepository.getById(1L);

        assertNotNull(actual);
        assertEquals("Semen", actual.getUserName());
        assertEquals("semen@epam.com", actual.getEmail());
    }

    @Test
    public void testUpdate() throws Exception {
        int actual = userRepository.update("Andrey", "qwe", "mail", 1L);

        assertEquals(1, actual);
    }

    @Test
    public void testDelete() throws Exception {
        userRepository.delete(1L);
        User actual = userRepository.getById(1L);

        assertNotNull(actual);
        assertEquals(false, actual.isActive());
    }
}