package com.epam.spring.dao;

import com.epam.spring.config.AppConfig;
import com.epam.spring.model.Tags;
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
public class TagsRepositoryTest {

    private final String tagName = "My new tag";
    @Autowired
    TagsRepository tagsRepository;

    @Before
    public void setUp() throws Exception {
        // watch resources update.sql
    }

    @Test
    public void testGetById() throws Exception {
        Tags actual = tagsRepository.getById(1L);

        assertNotNull(actual);
        assertEquals("dummy tag", actual.getName());
    }

    @Test
    public void testDeleteTag() throws Exception {
        tagsRepository.deleteById(1L);

        assertEquals(4, tagsRepository.count());
    }

    @Test
    public void testGetTagByName() throws Exception {
        List<Tags> actual = tagsRepository.getAllByName("NB");

        assertEquals(1, actual.size());
        assertEquals("NB", actual.get(0).getName());
    }

    @Test
    public void testUpdate() throws Exception {
        int actual = tagsRepository.update("new name", 2L);

        assertEquals(1, actual);
    }
}