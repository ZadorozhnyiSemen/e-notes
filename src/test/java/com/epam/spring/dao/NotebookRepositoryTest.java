package com.epam.spring.dao;

import com.epam.spring.config.AppConfig;
import com.epam.spring.model.Notebook;
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
public class NotebookRepositoryTest {

    private final String notebookName = "My notebook";

    @Autowired
    private NotebookRepository notebookRepository;

    @Before
    public void setUp() throws Exception {
        // watch resources update.sql
    }

    @Test
    public void testGetById() throws Exception {
        //when
        Notebook actual = notebookRepository.getById(1L);
        //then
        assertNotNull(actual);
        assertEquals("notebook1", actual.getName());
    }

    @Test
    public void testDeleteById() throws Exception {
        //when
        notebookRepository.deleteById(1L);
        //then
        assertEquals(5, notebookRepository.count());
    }

    @Test
    public void testFindByName() throws Exception {
        //when
        List<Notebook> list = notebookRepository.findByName("test test");

        //then
        assertEquals(1, list.size());
        assertEquals("test test", list.get(0).getName());
    }

    @Test
    public void testUpdate() throws Exception {
        int actual = notebookRepository.update("my note", 3L);

        assertEquals(1, actual);
    }
}