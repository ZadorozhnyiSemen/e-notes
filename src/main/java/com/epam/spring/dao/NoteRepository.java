package com.epam.spring.dao;

import com.epam.spring.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select n from Note n where n.id = ?1")
    Note getById(Long id);

    @Modifying
    @Query("delete from Note as n where n.id = ?1")
    void deleteById(Long id);

    @Query("select n from Note n where n.title = ?1")
    List<Note> getAllByTitle(String title);

    @Modifying
    @Query("update Note n set n.title = ?1, n.content = ?2 where n.id = ?3")
    Note update(String title, String content, Long id);
}
