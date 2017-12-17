package com.epam.spring.service;

import com.epam.spring.dao.UserRepository;
import com.epam.spring.model.User;
import com.epam.spring.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Before
    public void setUp() throws Exception {
        List<User> mockedUserList = Arrays.asList(
                new User("user1", "user1@epam.com", "qwerty", true),
                new User("user2", "user2@epam.com", "qwerty", true),
                new User("user3", "user3@epam.com", "qwerty", true)
        );

        List<User> mockedUser = Arrays.asList(new User("Semen", "semen@epam.com", "qwerty", true));


        when(userRepository.findAll()).thenReturn(mockedUserList);
        when(userRepository.update("userName", "pass", "email", 1L)).thenReturn(1);
        when(userRepository.findByUserName("Semen"))
                .thenReturn(mockedUser);
    }

    @Test
    public void testFindByUserName() throws Exception {
        List<User> actual = userService.findAllByUsername("Semen");

        verify(userRepository, times(1)).findByUserName(anyString());
        assertNotNull(actual);
        assertTrue("Semen".equals(actual.get(0).getUserName()));
        assertTrue("semen@epam.com".equals(actual.get(0).getEmail()));
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> actualUserList = userService.getAll();

        verify(userRepository, times(1)).findAll();
        assertEquals(3, actualUserList.size());
    }

    //TODO change ID passing
    @Ignore
    @Test
    public void testUpdate() throws Exception {
        int actual = userService.update(new User("userName", "email", "pass", true));

        verify(userRepository, times(1)).update("userName", "pass", "email", 1L);
        assertEquals(1, actual);
    }

}