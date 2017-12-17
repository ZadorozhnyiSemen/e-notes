package com.epam.spring.controller;

import com.epam.spring.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/notes")
public class NoteController {

  @Autowired
  private NoteService noteService;

  @RequestMapping(value = "/{userId}/{noteId}", method = RequestMethod.GET)
  public String getNote() {
    return "user";
  }

  @RequestMapping(value = "/{userId}/{noteId}", method = RequestMethod.DELETE)
  public String deleteNote() {
    return "user";
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
  public String getAllNotes() {
    return "user";
  }

  @RequestMapping(value = "/{usedId}/{tagId}", method = RequestMethod.GET)
  public String getTag() {
    return "user";
  }
}