package com.epam.spring.service;

import com.epam.spring.dao.NotebookRepository;
import com.epam.spring.model.Notebook;
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
public class NotebookServiceTest {

    @Mock
    NotebookRepository notebookRepository;

    @InjectMocks
    NotebookService notebookService;

    @Before
    public void setUp() throws Exception {
        Notebook mockedNotebook = new Notebook("Some name", null);
        List<Notebook> mockedNotebookList = Arrays.asList(
                new Notebook("notebook1", null),
                new Notebook("notebook1", null)
        );

        when(notebookRepository.getById(anyLong())).thenReturn(mockedNotebook);
        when(notebookRepository.findByName("notebook1")).thenReturn(mockedNotebookList);
    }

    @Test
    public void testGetById() throws Exception {
        Notebook actual = notebookService.getById(1L);

        verify(notebookRepository, times(1)).getById(anyLong());
        assertNotNull(actual);
    }

    @Test
    public void testFindByName() throws Exception {
        List<Notebook> actualList = notebookService.findByName("notebook1");

        verify(notebookRepository, times(1)).findByName(anyString());
        assertNotNull(actualList);
        assertEquals(2, actualList.size());
    }

}