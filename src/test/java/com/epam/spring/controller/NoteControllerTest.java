package com.epam.spring.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.spring.service.NoteService;
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
public class NoteControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private NoteService noteService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                             .build();
  }

  @Test
  public void getNoteById_should_return_1_note() throws Exception {
    // given
    String url = "/api/notes/1";
    String expectedTitle = noteService.getById(1L)
                                      .getTitle();

    // when then
    mockMvc.perform(get(url))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.title", is(expectedTitle)));
  }

  @Test
  public void deleteNote_should_delete_1_note() throws Exception {
    // given
    String url = "/api/notes/1";
    int expectedSize = 1;

    // when then
    mockMvc.perform(delete(url))
           .andExpect(status().isOk());
    int actualSize = noteService.getAll()
                                .size();
    assertThat(actualSize, is(expectedSize));
  }
}