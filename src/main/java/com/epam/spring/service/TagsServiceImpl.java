package com.epam.spring.service;

import com.epam.spring.dao.TagsRepository;
import com.epam.spring.model.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsRepository tagsRepository;

    public Tags getById(Long id) {
        return tagsRepository.getById(id);
    }

    public void deleteById(Long id) {
        tagsRepository.deleteById(id);
    }

    public List<Tags> findByName(String name) {
        return tagsRepository.getAllByName(name);
    }
}
