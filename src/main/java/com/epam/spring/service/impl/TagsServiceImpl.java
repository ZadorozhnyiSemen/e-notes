package com.epam.spring.service.impl;

import com.epam.spring.dao.TagsRepository;
import com.epam.spring.model.Tags;
import com.epam.spring.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public List<Tags> getAll() {
        return tagsRepository.findAll();
    }

    public Tags getById(Long id) {
        return tagsRepository.getById(id);
    }

    @Override
    public Tags update(Tags entity) {
        Tags tag = tagsRepository.getById(entity.getId());
        tag.setName(entity.getName());
        return tagsRepository.save(tag);
    }

    @Override
    public Tags create(Tags entity) {
        return tagsRepository.save(entity);
    }

    public void deleteById(Long id) {
        tagsRepository.deleteById(id);
    }

    public List<Tags> findByName(String name) {
        return tagsRepository.getAllByName(name);
    }

}
