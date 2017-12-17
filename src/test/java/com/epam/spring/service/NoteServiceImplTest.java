package com.epam.spring.service;

import com.epam.spring.dao.NoteRepository;
import com.epam.spring.model.Note;
import com.epam.spring.service.impl.NoteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceImplTest {

    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    NoteService noteService = new NoteServiceImpl();

    @Before
    public void setUp() throws Exception {
        Note mockedNote = new Note("title", "content", null);
        List<Note> mockedNotesList = Arrays.asList(
                new Note("cool", "content", null),
                new Note("cool", "content", null)
        );

        when(noteRepository.getById(anyLong())).thenReturn(mockedNote);
        when(noteRepository.update("title", "content", 1L)).thenReturn(1);
        when(noteRepository.getAllByTitle("cool")).thenReturn(mockedNotesList);
    }

    @Test
    public void testGetById() throws Exception {
        Note actual = noteService.getById(1L);

        verify(noteRepository, times(1)).getById(anyLong());
        assertNotNull(actual);
    }

    @Test
    public void testGetAllByName() throws Exception {
        List<Note> actualList = noteService.findByName("cool");

        verify(noteRepository, times(1)).getAllByTitle(anyString());
        assertNotNull(actualList);
        assertEquals(2, actualList.size());
    }

    @Test
    public void testUpdate() throws Exception {
        int actual = noteService.update(1L, new Note("title", "content", null));

        verify(noteRepository, times(1)).update("title", "content", 1L);
        assertEquals(1, actual);
    }

}