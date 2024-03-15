package com.managermoneyapi.controllers;

import com.managermoneyapi.dto.CategoryDto;
import com.managermoneyapi.entity.Category;
import com.managermoneyapi.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/categories")
@Validated
public class CategoryController extends BaseController<Category, CategoryDto, CategoryService>{
}
