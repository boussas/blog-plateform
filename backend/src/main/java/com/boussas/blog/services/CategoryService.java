package com.boussas.blog.services;

import com.boussas.blog.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoryService {
    List<Category> getCategories();
    Category createCategory(Category category);
    void deleteCategory(UUID id);
}
