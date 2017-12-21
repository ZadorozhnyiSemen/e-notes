package com.epam.spring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.spring.dao.UserRepository;
import com.epam.spring.model.User;
import com.epam.spring.service.impl.UserServiceImpl;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

  @Mock
  UserRepository userRepository;

  @Mock
  User user;

  @InjectMocks
  UserService userService = new UserServiceImpl();

  @Before
  public void setUp() throws Exception {
    List<User> mockedUserList = getUsers();

    List<User> mockedUser = Arrays.asList(new User("Semen", "semen@epam.com", "qwerty", true));

    when(userRepository.update("userName", "pass", "email", 1L)).thenReturn(1);
    when(userRepository.getById(40L)).thenReturn(user);
    when(userRepository.findByUserName("Semen"))
        .thenReturn(mockedUser);

  }

  @Test
  public void testFindByUserName() throws Exception {
    List<User> mockedUserList = getUsers();
    when(userRepository.findAll()).thenReturn(mockedUserList);

    List<User> actual = userService.findAllByUsername("Semen");

    verify(userRepository, times(1)).findByUserName(anyString());
    assertNotNull(actual);
    assertTrue("Semen".equals(actual.get(0)
                                    .getUserName()));
    assertTrue("semen@epam.com".equals(actual.get(0)
                                             .getEmail()));
  }

  @Test
  public void testFindByUserNameWildcard() throws Exception {
    List<User> emptyUserList = Collections.emptyList();
  }

  @Test
  public void testGetAll() throws Exception {
    when(userRepository.findAll()).thenReturn(getUsers());
    List<User> actualUserList = userService.getAll();

    verify(userRepository, times(1)).findAll();
    assertEquals(3, actualUserList.size());
  }

  @Test
  public void testUpdate() throws Exception {
    int actual = userService.update(1L, new User("userName", "email", "pass", true));

    verify(userRepository, times(1)).update("userName", "pass", "email", 1L);
    assertEquals(1, actual);
  }

  @Test
  public void testDelete() throws Exception {
    when(user.isActive()).thenReturn(true)
                         .thenReturn(false);
    when(user.getId()).thenReturn(40L);

    User deleted = userService.delete(user.getId());

    verify(user, times(1)).isActive();
    verify(userRepository, times(1)).getById(40L);
    verify(userRepository, times(1)).delete(40L);
    assertFalse(deleted.isActive());
  }


  private List<User> getUsers() {
    return Arrays.asList(
        new User("user1", "user1@epam.com", "qwerty", true),
        new User("user2", "user2@epam.com", "qwerty", true),
        new User("user3", "user3@epam.com", "qwerty", true)
    );
  }
}