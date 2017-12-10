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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserRepositoryTest {

    private static final String TAG_NAME = "Awesome tag";

    @Autowired
    UserRepository userRepository;

    private User expected;
    private Tags tag;

    @Before
    public void setUp() throws Exception {
        List<Tags> tags = new ArrayList<>();
        User user = new User("Andrey", "andrey@epam.com", "qwerty", true);
        tag = new Tags(user, TAG_NAME);
        tags.add(tag);
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
        assertEquals(expected, actual);
        assertTrue("User don't have tag", !expected.getTags().isEmpty());
        assertTrue(expected.getTags().get(0).getName().equals(TAG_NAME));
    }

    @Test
    public void testRepoSavesUser() throws Exception {
        assertEquals(1, userRepository.count());
    }
}