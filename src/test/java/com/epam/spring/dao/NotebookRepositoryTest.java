package com.epam.spring.dao;

import com.epam.spring.config.AppConfig;
import com.epam.spring.model.Notebook;
import org.junit.After;
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

    private Notebook expected;

    @Before
    public void setUp() throws Exception {
        Notebook notebook = new Notebook(notebookName, null);
        expected = notebookRepository.save(notebook);
    }

    @After
    public void tearDown() throws Exception {
        notebookRepository.deleteAll();
    }

    @Test
    public void testGetById() throws Exception {
        //when
        Notebook actual = notebookRepository.getById(expected.getId());
        //then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteById() throws Exception {
        //when
        notebookRepository.deleteById(expected.getId());
        //then
        assertEquals(0, notebookRepository.count());
    }

    @Test
    public void testFindByName() throws Exception {
        //given
        Notebook secondNotebook = new Notebook(notebookName, null);
        notebookRepository.save(secondNotebook);

        //when
        List<Notebook> list = notebookRepository.findByName(notebookName);

        //then
        assertEquals(2, list.size());
        assertEquals(notebookName, list.get(0).getName());
        assertEquals(notebookName, list.get(1).getName());
    }
}