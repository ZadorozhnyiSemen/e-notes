package com.epam.spring.service.impl;

import com.epam.spring.dao.NoteRepository;
import com.epam.spring.model.Note;
import com.epam.spring.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note getById(Long id) {
        return noteRepository.getById(id);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public List<Note> findByName(String title) {
        return noteRepository.getAllByTitle(title);
    }

}
