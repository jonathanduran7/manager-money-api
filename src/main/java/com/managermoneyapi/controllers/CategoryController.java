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
public class CategoryController extends BaseController<Category, CategoryDto, CategoryService>{
}
