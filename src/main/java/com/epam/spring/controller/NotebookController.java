package com.epam.spring.controller;

import com.epam.spring.model.Notebook;
import com.epam.spring.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/notebooks")
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @GetMapping
    public ResponseEntity<List<Notebook>> getNotebook(@RequestParam(value = "s", required = false) String s) {
        if (s != null) {
            List<Notebook> searchList = notebookService.findByName(s);
            return new ResponseEntity<>(searchList, HttpStatus.OK);
        } else {
            List<Notebook> notebooks = notebookService.getAll();
            return new ResponseEntity<>(notebooks, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{notebookId}")
    public ResponseEntity<Notebook> getNotebookById(@PathVariable Long notebookId) {
        Notebook notebook = notebookService.getById(notebookId);
        return new ResponseEntity<>(notebook, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNotebook(@RequestBody Notebook notebook) {
        Notebook created = notebookService.create(notebook);
        return new ResponseEntity<Object>(created, HttpStatus.OK);
    }

    @PostMapping(value = "/{notebookId}")
    public ResponseEntity<?> updateNotebook(@RequestBody Notebook notebook, @PathVariable Long notebookId) {
        Notebook updated = notebookService.update(notebookId, notebook);
        return new ResponseEntity<Object>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{notebookId}")
    public ResponseEntity<?> deleteNotebook(@PathVariable Long notebookId) {
        notebookService.deleteById(notebookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
