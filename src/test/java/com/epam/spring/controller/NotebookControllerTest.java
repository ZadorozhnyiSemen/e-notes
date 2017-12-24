package com.epam.spring.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.spring.service.NotebookService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class NotebookControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private NotebookService notebookService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                             .build();
  }

  @Test
  public void getNotebook_should_return_1_note() throws Exception {
    // given
    String url = "/api/notebooks?s=1";
    String expectedTitle = notebookService.getById(1L)
                                          .getName();

    // when then
    mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].name", is(expectedTitle)));
  }

  @Test
  public void getNotebooks_should_return_names() throws Exception {
    //given
    String url = "/api/notebooks";

    String expectedTitle1 = "notebook1";
    String expectedTitle2 = "my notebook";
    String expectedTitle3 = "awesome notebook";
    String expectedTitle4 = "test test";
    String expectedTitle5 = "just trying";
    String expectedTitle6 = "hi all nice notebook";

    //when then
    mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].name", is(expectedTitle1)))
           .andExpect(jsonPath("$[1].name", is(expectedTitle2)))
           .andExpect(jsonPath("$[2].name", is(expectedTitle3)))
           .andExpect(jsonPath("$[3].name", is(expectedTitle4)))
           .andExpect(jsonPath("$[4].name", is(expectedTitle5)))
           .andExpect(jsonPath("$[5].name", is(expectedTitle6)));

  }

  @Ignore
  @Test
  public void createNotebook() {
  }

  @Ignore
  @Test
  public void updateNotebook() {
  }

  @Test
  public void deleteNotebook() throws Exception {
    //given
    String url = "/api/notebooks/1";

    int expectedSize = 5;

    // when
    mockMvc.perform(delete(url));
    int actualSize = notebookService.getAll()
                                    .size();

    //then
    assertThat(actualSize, is(expectedSize));
  }
}