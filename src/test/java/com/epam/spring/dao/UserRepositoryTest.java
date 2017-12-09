package com.epam.spring.dao;

import com.epam.spring.config.AppConfig;
import com.epam.spring.model.Tags;
import com.epam.spring.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private User expected;

    @Before
    public void setUp() throws Exception {
        Set<Tags> tags = new HashSet<>();
        User user = new User("Andrey", "andrey@epam.com", "qwerty", true);
        tags.add(new Tags(user, "awsm tag"));
        user.setTags(tags);
        expected = userRepository.save(user);
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void testFindUserByUsernameAndPasswordGetsUser() throws Exception {
        User actual = userRepository.findByUserNameAndPassword("Andrey", "qwerty");
        System.out.println(actual);
        System.out.println(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testRepoSavesUser() throws Exception {
        assertEquals(1, userRepository.count());
    }
}