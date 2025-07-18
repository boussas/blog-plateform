package com.boussas.blog.controllers;

import com.boussas.blog.entities.Category;
import com.boussas.blog.entities.dtos.ApiErrorResponse;
import com.boussas.blog.entities.dtos.CategoryDto;
import com.boussas.blog.entities.dtos.CreateCategoryRequest;
import com.boussas.blog.mappers.CategoryMapper;
import com.boussas.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categories= categoryService.getCategories()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CreateCategoryRequest categoryDto) {
            Category created= categoryMapper.toEntity(categoryDto);
            Category saved=  categoryService.createCategory(created);
            return new ResponseEntity<>(categoryMapper.toDto(saved), HttpStatus.CREATED);
    }
    @DeleteMapping(path= "/{category_id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID category_id) {
        categoryService.deleteCategory(category_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
