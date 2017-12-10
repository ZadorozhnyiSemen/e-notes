package com.epam.spring.service;

import com.epam.spring.dao.NotebookRepository;
import com.epam.spring.model.Notebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotebookService {

    @Autowired
    NotebookRepository notebookRepository;

    Notebook getById(Long id) {
        return notebookRepository.getById(id);
    }

    void deleteById(Long id) {
        notebookRepository.deleteById(id);
    }

    List<Notebook> findByName(String name) {
        return notebookRepository.findByName(name);
    }
}
