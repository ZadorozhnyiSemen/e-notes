package com.epam.spring.dao;

import com.epam.spring.config.AppConfig;
import com.epam.spring.model.Tags;
import org.junit.After;
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

    private final String tagName = "My new tag";
    @Autowired
    TagsRepository tagsRepository;

    @After
    public void tearDown() throws Exception {
        tagsRepository.deleteAll();
    }

    @Test
    public void testCreateTag() throws Exception {
        Tags tags = new Tags(null, tagName);

        Tags expected = tagsRepository.save(tags);

        Tags actual = tagsRepository.getById(expected.getId());

        assertEquals(tags, actual);
    }

    @Test
    public void testDeleteTag() throws Exception {
        Tags tags = new Tags(null, tagName);

        Tags expected = tagsRepository.save(tags);

        tagsRepository.deleteById(expected.getId());

        assertEquals(0, tagsRepository.count());
    }

    @Test
    //TODO involve user_id (future stages)
    public void testGetTagByName() throws Exception {
        String tagName = this.tagName;
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