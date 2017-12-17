package com.epam.spring.dao;

import com.epam.spring.config.AppConfig;
import com.epam.spring.model.Note;
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
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Before
    public void setUp() throws Exception {
        // watch resources/update.sql
    }

    @Test
    public void getById() throws Exception {
        Note actual = noteRepository.getById(1L);

        assertNotNull(actual);
        assertEquals("awesome title", actual.getTitle());
        assertEquals("incredible content", actual.getContent());
    }

    @Test
    public void testDeleteById() throws Exception {
        noteRepository.deleteById(1L);

        assertEquals(1, noteRepository.count());
    }

    @Test
    public void testGetAllByTitle() throws Exception {
        List<Note> list = noteRepository.getAllByTitle("awesome title 2");

        assertEquals(1, list.size());
        assertEquals("awesome title 2", list.get(0).getTitle());
        assertEquals("incredible content 2", list.get(0).getContent());
    }

    @Test
    public void testUpdate() throws Exception {
        int actual = noteRepository.update("some new title", "bla bla", 2L);

        assertEquals(1, actual);
    }

}