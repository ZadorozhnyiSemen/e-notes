package com.epam.spring.service.impl;

import com.epam.spring.dao.TagsRepository;
import com.epam.spring.model.Tags;
import com.epam.spring.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public Tags update(Long id, Tags entity) {
        Tags tag = tagsRepository.getById(id);
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
        List<Tags> byName = tagsRepository.getAllByName(name);
        if (byName.size() == 0) {
            List<Tags> searchList = tagsRepository.findAll();
            return searchList.stream()
                    .filter(tag -> tag.getName().contains(name))
                    .collect(Collectors.toList());
        } else {
            return byName;
        }
    }

}
