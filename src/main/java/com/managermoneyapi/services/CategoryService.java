package com.managermoneyapi.services;

import com.managermoneyapi.dto.CategoryDto;
import com.managermoneyapi.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(CategoryDto categoryDto);
    Optional<Category> update(Long id, CategoryDto categoryDto);
    void delete(Long id);
}
