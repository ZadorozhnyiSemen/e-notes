package com.epam.spring.service.impl;

import com.epam.spring.dao.NotebookRepository;
import com.epam.spring.model.Notebook;
import com.epam.spring.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotebookServiceImpl implements NotebookService {

    @Autowired
    private NotebookRepository notebookRepository;

    public List<Notebook> getAll() {
        return notebookRepository.findAll();
    }

    public Notebook update(Long id, Notebook updated) {
        Notebook notebook = notebookRepository.getById(id);
        notebook.setName(updated.getName());
        return notebookRepository.save(notebook);
    }

    @Override
    public Notebook create(Notebook entity) {
        return notebookRepository.save(entity);
    }

    public Notebook getById(Long id) {
        return notebookRepository.getById(id);
    }

    public void deleteById(Long id) {
        notebookRepository.deleteById(id);
    }

    public List<Notebook> findByName(String name) {
        return notebookRepository.findByName(name);
    }
}
