package com.epam.spring.dao;

import com.epam.spring.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagsRepository extends JpaRepository<Tags, Long> {

    @Query("select t from Tags t where t.id = ?1")
    Tags getById(Long id);

    @Modifying
    @Query("delete from Tags as t where t.id = ?1")
    void deleteById(Long id);

    @Query("select t from Tags t where t.name = ?1")
    List<Tags> getAllByName(String name);

    @Modifying
    @Query("update Tags t set t.name = ?1 where t.id = ?2")
    int update(String name, Long id);
}
