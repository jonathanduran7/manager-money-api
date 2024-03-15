package com.managermoneyapi.repositories;

import com.managermoneyapi.entity.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Save Category")
    void testSaveCategory() {
        Category categoryDB = Category.builder()
                .name("Category Test")
                .created_at("2021-08-01")
                .updated_at("2021-08-01")
                .build();

        Category accountSaved = categoryRepository.save(categoryDB);

        assertThat(accountSaved).isNotNull();
        assertThat(accountSaved.getName()).isEqualTo("Category Test");
    }

    @Test
    @DisplayName("Get all categories")
    void testGetAllCategories(){
        Category categoryDB = Category.builder()
                .name("Category Test")
                .created_at("2021-08-01")
                .updated_at("2021-08-01")
                .build();

        Category categoryDB2 = Category.builder()
                .name("Category Test 2")
                .created_at("2021-08-01")
                .updated_at("2021-08-01")
                .build();

        categoryRepository.save(categoryDB);
        categoryRepository.save(categoryDB2);

        assertThat(categoryRepository.findAll()).isNotEmpty();
        assertThat(categoryRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Get category by id")
    void testGetCategoryById(){
        Category categoryDB = Category.builder()
                .name("Category Test")
                .created_at("2021-08-01")
                .updated_at("2021-08-01")
                .build();

        Category categorySaved = categoryRepository.save(categoryDB);

        assertThat(categoryRepository.findById(categorySaved.getId())).isNotEmpty();
        assertThat(categoryRepository.findById(categorySaved.getId())
                .get().getName())
                .isEqualTo("Category Test");
    }

    @Test
    @DisplayName("Update category")
    void testUpdateCategory(){
        Category categoryDB = Category.builder()
                .name("Category Test")
                .created_at("2021-08-01")
                .updated_at("2021-08-01")
                .build();

        Category categorySaved = categoryRepository.save(categoryDB);
        categorySaved.setName("Category Test Updated");

        Category categoryUpdated = categoryRepository.save(categorySaved);

        assertThat(categoryUpdated).isNotNull();
        assertThat(categoryUpdated.getName()).isEqualTo("Category Test Updated");
    }
}