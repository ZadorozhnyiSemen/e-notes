package com.epam.spring.service;

import com.epam.spring.dao.NotebookRepository;
import com.epam.spring.model.Notebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotebookServiceImpl implements NotebookService {

    @Autowired
    private NotebookRepository notebookRepository;

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
