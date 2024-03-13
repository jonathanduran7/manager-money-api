package com.managermoneyapi.services;

import com.managermoneyapi.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    Optional<Category> update(Long id, Category category);
    void delete(Long id);
}
