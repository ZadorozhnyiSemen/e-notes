package com.epam.spring.dao;

import com.epam.spring.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {

    @Query("select n from Notebook n where n.id = ?1")
    Notebook getById(Long id);

    @Modifying
    @Query("delete from Notebook as n where n.id = ?1")
    void deleteById(Long id);

    @Query("select n from Notebook n where n.name = ?1")
    List<Notebook> findByName(String name);

    @Modifying
    @Query("update Notebook n set n.name = ?1 where n.id = ?2")
    int update(String name, Long id);
}
