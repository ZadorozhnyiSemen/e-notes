package com.epam.spring.service;

import com.epam.spring.dao.UserRepository;
import com.epam.spring.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Before
    public void setUp() throws Exception {
        List<User> mockedUserList = Arrays.asList(
                new User("user1", "user1@epam.com", "qwerty", true),
                new User("user2", "user2@epam.com", "qwerty", true),
                new User("user3", "user3@epam.com", "qwerty", true)
        );

        User mockedUser = new User("Semen", "semen@epam.com", "qwerty", true);


        when(userRepository.getAll()).thenReturn(mockedUserList);
        when(userRepository.findByUserNameAndPassword("Semen", "semen@epam.com"))
                .thenReturn(mockedUser);
    }

    @Test
    public void findByUserNameAndPassword() throws Exception {
        User actual = userService.findByUserNameAndPassword("Semen", "semen@epam.com");

        assertNotNull(actual);
        assertTrue("Semen".equals(actual.getUserName()));
        assertTrue("semen@epam.com".equals(actual.getEmail()));
    }

    @Test
    public void getAll() throws Exception {
        List<User> actualUserList = userService.getAll();

        assertEquals(3, actualUserList.size());
    }

}