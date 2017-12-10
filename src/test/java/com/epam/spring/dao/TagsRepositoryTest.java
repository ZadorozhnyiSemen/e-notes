package com.epam.spring.dao;

import com.epam.spring.config.AppConfig;
import com.epam.spring.model.Tags;
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

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = AppConfig.class)
public class TagsRepositoryTest {

    @Autowired
    TagsRepository tagsRepository;

    @Before
    public void setUp() throws Exception {
        /*User user = new User("Semen", "semen@mail.ru", "somepass", true);
        Tags tags = new Tags(user, "My new tag");

        tagsRepository.save(tags);*/
    }

    @After
    public void tearDown() throws Exception {
        tagsRepository.deleteAll();
    }

    @Test
    public void testCreateTag() throws Exception {
        Tags tags = new Tags(null, "My new tag");

        Tags expected = tagsRepository.save(tags);

        Tags actual = tagsRepository.getById(expected.getId());

        assertEquals(tags, actual);
    }

    @Test
    public void testDeleteTag() throws Exception {
        Tags tags = new Tags(null, "My new tag");

        Tags expected = tagsRepository.save(tags);

        tagsRepository.deleteById(expected.getId());

        assertEquals(0, tagsRepository.count());
    }

    @Test
    public void testGetTagByName() throws Exception {
        String tagName = "My new tag";
        Tags firstTag = new Tags(null, tagName);
        Tags secondTag = new Tags(null, tagName);

        tagsRepository.save(firstTag);
        tagsRepository.save(secondTag);

        List<Tags> actual = tagsRepository.getAllByName(tagName);

        assertEquals(2, actual.size());
        assertEquals(tagName, actual.get(0).getName());
        assertEquals(tagName, actual.get(1).getName());
    }
}