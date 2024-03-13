package com.managermoneyapi.services.impl;

import com.managermoneyapi.entity.Category;
import com.managermoneyapi.repositories.CategoryRepository;
import com.managermoneyapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> update(Long id, Category category) {

        Category categoryToUpdate = categoryRepository.findById(id).orElse(null);

        if (categoryToUpdate != null) {
            categoryToUpdate.setName(category.getName());
            return Optional.of(categoryRepository.save(categoryToUpdate));
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
