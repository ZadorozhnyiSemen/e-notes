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

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    public Note getById(Long id) {
        return noteRepository.getById(id);
    }

    @Override
    public Note update(Long id, Note entity) {
        Note note = noteRepository.getById(id);
        note.setTitle(entity.getTitle());
        note.setContent(entity.getContent());
        return noteRepository.save(note);
    }

    @Override
    public Note create(Note entity) {
        return noteRepository.save(entity);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public List<Note> findByName(String title) {
        return noteRepository.getAllByTitle(title);
    }

}
