package com.epam.spring.service;

import com.epam.spring.dao.NoteRepository;
import com.epam.spring.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    Note getById(Long id) {
        return noteRepository.getById(id);
    }

    void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    List<Note> getAllByTitle(String title) {
        return noteRepository.getAllByTitle(title);
    }
}
