package com.epam.spring.controller;

import com.epam.spring.model.Tags;
import com.epam.spring.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tags")
public class TagController {

    @Autowired
    private TagsService tagsService;

    @GetMapping
    public ResponseEntity<List<Tags>> getAllTags(@PathVariable(value = "s", required = false) String s) {
        if (s != null) {
            List<Tags> searchTags = tagsService.findByName(s);
            return new ResponseEntity<>(searchTags, HttpStatus.OK);
        } else {
            List<Tags> allTags = tagsService.getAll();
            return new ResponseEntity<>(allTags, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{tagId}")
    public ResponseEntity<Tags> getTagById(@PathVariable Long tagId) {
        Tags tags = tagsService.getById(tagId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tags> create(@RequestBody Tags tag) {
        Tags created = tagsService.create(tag);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @PostMapping(value = "/{tagId}")
    public ResponseEntity<Tags> update(@RequestBody Tags tag, @PathVariable Long tagId) {
        int updated = tagsService.update(tagId, tag);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{tagId}")
    public ResponseEntity<?> delete(@PathVariable Long tagId) {
        tagsService.deleteById(tagId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
