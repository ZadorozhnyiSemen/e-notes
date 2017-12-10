package com.epam.spring.dao;

import com.epam.spring.config.AppConfig;
import com.epam.spring.model.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
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

    @NonNull
    private final String noteTitle = "Some title";
    @NonNull
    private final String noteContent = "Remember to do everything";

    @Autowired
    private NoteRepository noteRepository;

    private Note expected;

    @Before
    public void setUp() throws Exception {
        Note note = new Note(noteTitle, noteContent, null);
        expected = noteRepository.save(note);
    }

    @After
    public void tearDown() throws Exception {
        noteRepository.deleteAll();
    }

    @Test
    public void getById() throws Exception {
        Note actual = noteRepository.getById(expected.getId());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteById() throws Exception {
        noteRepository.deleteById(expected.getId());

        assertEquals(0, noteRepository.count());
    }

    @Test
    public void getAllByName() throws Exception {
        Note secondNote = new Note(noteTitle, noteContent, null);
        noteRepository.save(secondNote);

        List<Note> list = noteRepository.getAllByName(noteTitle);

        assertEquals(2, list.size());
        assertEquals(noteTitle, list.get(0).getTitle());
        assertEquals(noteContent, list.get(1).getContent());
    }

}