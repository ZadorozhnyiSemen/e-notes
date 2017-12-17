package com.epam.spring.controller;

import com.epam.spring.model.Tags;
import com.epam.spring.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/tags")
public class TagController {

    @Autowired
    private TagsService tagsService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Tags>> getAllTags() {
        List<Tags> allTags = tagsService.getAll();
        return new ResponseEntity<>(allTags, HttpStatus.OK);
    }

    @GetMapping(value = "/{tagId}")
    public ResponseEntity<Tags> getTagById(@PathVariable Long tagId) {
        Tags tags = tagsService.getById(tagId);
        return new ResponseEntity<Tags>(tags, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Tags> create(@RequestBody Tags tag) {
        Tags created = tagsService.create(tag);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @PostMapping(value = "/{tagId}")
    public ResponseEntity<Tags> update(@RequestBody Tags tag, @PathVariable Long tagId) {
        Tags updated = tagsService.update(tagId, tag);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{tagId}")
    public ResponseEntity<?> delete(@PathVariable Long tagId) {
        tagsService.deleteById(tagId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
