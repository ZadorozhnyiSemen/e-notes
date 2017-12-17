package com.epam.spring.service.impl;

import com.epam.spring.dao.NoteRepository;
import com.epam.spring.model.Note;
import com.epam.spring.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public int update(Long id, Note entity) {
        return noteRepository.update(entity.getTitle(), entity.getContent(), id);
    }

    @Override
    public Note create(Note entity) {
        return noteRepository.save(entity);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public List<Note> findByName(String title) {
        List<Note> byTitle = noteRepository.getAllByTitle(title);
        if (byTitle.size() == 0) {
            List<Note> searchList = noteRepository.findAll();
            return searchList.stream()
                    .filter(note -> note.getTitle().contains(title))
                    .collect(Collectors.toList());
        } else {
            return byTitle;
        }
    }

}
