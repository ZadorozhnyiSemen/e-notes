package com.epam.spring.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.spring.service.TagsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TagControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private TagsService tagsService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                             .build();
  }

  @Test
  public void getAllTags() {
  }

  @Test
  public void getTagById_should_return_1_tag() throws Exception {
    //given
    String url = "/api/tags/2";
    String expectedTitle = tagsService.getById(2L)
                                      .getName();

    //when
    mockMvc.perform(get(url))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name", is(expectedTitle)));
  }

  @Test
  public void create() {
  }

  @Test
  public void update() {
  }

  @Test
  public void deleteById_should_return_size_4() throws Exception {
    //given
    String url = "/api/tags/1";

    int expectedSize = 4;

    // when
    mockMvc.perform(delete(url));
    int actualSize = tagsService.getAll()
                                .size();

    //then
    assertThat(actualSize, is(expectedSize));
  }
}