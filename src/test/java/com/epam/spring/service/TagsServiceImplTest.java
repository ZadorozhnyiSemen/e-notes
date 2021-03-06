package com.epam.spring.service;

import com.epam.spring.dao.TagsRepository;
import com.epam.spring.model.Tags;
import com.epam.spring.service.impl.TagsServiceImpl;
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
public class TagsServiceImplTest {

    @Mock
    TagsRepository tagsRepository;

    @InjectMocks
    TagsService tagsService = new TagsServiceImpl();

    @Before
    public void setUp() throws Exception {
        Tags mockedTag = new Tags(null, "tag");
        List<Tags> mockedTags = Arrays.asList(
                new Tags(null, "tagname"),
                new Tags(null, "tagname")
        );

        when(tagsRepository.getById(anyLong())).thenReturn(mockedTag);
        when(tagsRepository.update("name", 1L)).thenReturn(1);
        when(tagsRepository.getAllByName("tagname")).thenReturn(mockedTags);
    }

    @Test
    public void testGetById() throws Exception {
        Tags actual = tagsService.getById(1L);

        verify(tagsRepository, times(1)).getById(anyLong());
        assertNotNull(actual);
    }

    @Test
    public void testGetAllByName() throws Exception {
        List<Tags> actualList = tagsService.findByName("tagname");

        verify(tagsRepository, times(1)).getAllByName(anyString());
        assertNotNull(actualList);
        assertEquals(2, actualList.size());
    }

    @Test
    public void testUpdate() throws Exception {
        int actual = tagsService.update(1L, new Tags(null, "name"));

        verify(tagsRepository, times(1)).update("name", 1L);
        assertEquals(1, actual);
    }

}