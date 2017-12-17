package com.epam.spring.controller;

import com.epam.spring.model.Note;
import com.epam.spring.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getNotes(@RequestParam(name = "s", required = false) String s) {
        if (s != null) {
            List<Note> searchList = noteService.findByName(s);
            return new ResponseEntity<>(searchList, HttpStatus.OK);
        } else {
            List<Note> noteList = noteService.getAll();
            return new ResponseEntity<>(noteList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{noteId}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long noteId) {
        Note note = noteService.getById(noteId);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable Long noteId) {
        noteService.deleteById(noteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/{noteId}")
    public ResponseEntity<Note> updateNote(@RequestBody Note note, @PathVariable Long noteId) {
        int updated = noteService.update(noteId, note);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note created = noteService.create(note);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }
}
