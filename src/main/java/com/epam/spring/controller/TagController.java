package com.epam.spring.controller;

import com.epam.spring.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/tags")
public class TagController {

  @Autowired
  private TagsService tagsService;

  @RequestMapping(value = "/{usedId}/{tagId}", method = RequestMethod.DELETE)
  public String deleteTag() {
    return "user";
  }

  @RequestMapping(value = "/{usedId}", method = RequestMethod.POST)
  public String findTag() {
    return "user";
  }

  @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.GET)
  public String getNoteTags() {
    return "user";
  }

}
