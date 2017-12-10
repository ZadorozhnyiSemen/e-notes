package com.epam.spring.service;

import com.epam.spring.dao.TagsRepository;
import com.epam.spring.model.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsService {

    @Autowired
    TagsRepository tagsRepository;

    Tags getById(Long id) {
        return tagsRepository.getById(id);
    }

    void deleteById(Long id) {
        tagsRepository.deleteById(id);
    }

    List<Tags> getAllByName(String name) {
        return tagsRepository.getAllByName(name);
    }
}
