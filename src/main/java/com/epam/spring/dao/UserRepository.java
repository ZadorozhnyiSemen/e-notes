package com.epam.spring.dao;

import com.epam.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.userName = ?1")
    List<User> findByUserName(String userName);

    @Query("select u from User u where u.id = ?1")
    User getById(Long id);

    @Modifying
    @Query("update User u set u.active = false where u.id = ?1")
    void delete(Long id);

    @Modifying
    @Query("update User u set u.userName = ?1, u.password = ?2, u.email = ?3 where u.id = ?4")
    User update(String userName, String password, String email, Long id);
}
