package com.epam.spring.controller;

import com.epam.spring.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/notebooks")
public class NotebookController {

  @Autowired
  private NotebookService notebookService;

  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  public String getNotebook() {
    return "user";
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
  public String createNotebook() {
    return "user";
  }

  @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.POST)
  public String updateNotebook() {
    return "user";
  }

  @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.DELETE)
  public String deleteNotebook() {
    return "user";
  }
}
