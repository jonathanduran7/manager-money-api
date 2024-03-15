package com.managermoneyapi.services.impl;

import com.managermoneyapi.dto.CategoryDto;
import com.managermoneyapi.entity.Category;
import com.managermoneyapi.repositories.CategoryRepository;
import com.managermoneyapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Category save(CategoryDto categoryDto) {
        Category categorySave = Category
                .builder()
                .name(categoryDto.getName())
                .created_at(LocalDate.now().toString())
                .updated_at(LocalDate.now().toString())
                .build();

        return categoryRepository.save(categorySave);
    }

    @Override
    public Optional<Category> update(Long id, CategoryDto categoryDto) {

        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()){
            Category categoryToUpdate = categoryOptional.get();
            categoryToUpdate.setName(categoryDto.getName());
            return Optional.of(categoryRepository.save(categoryToUpdate));
        }

        return Optional.empty();
    }
}
