package com.managermoneyapi.controllers;

import com.managermoneyapi.dto.CategoryDto;
import com.managermoneyapi.entity.Category;
import com.managermoneyapi.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ResponseBody
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Category> findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public Category save(@RequestBody @Valid CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Optional<Category> update(@PathVariable Long id, @RequestBody @Valid CategoryDto categoryDto) {
        return categoryService.update(id, categoryDto);
    }

}
