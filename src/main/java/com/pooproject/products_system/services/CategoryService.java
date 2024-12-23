package com.pooproject.products_system.services;

import com.pooproject.products_system.domain.category.Category;
import com.pooproject.products_system.dao.CategoryDAO;

import java.util.List;
import java.util.Optional;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    public Category save(Category category) {
        return categoryDAO.save(category);
    }

    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(categoryDAO.findById(id));
    }

    public Optional<Category> findByName(String name) {
        return Optional.ofNullable(categoryDAO.findByName(name));
    }

    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    public Category update(Category category) {
        return categoryDAO.update(category);
    }

    public void delete(Long id) {
        categoryDAO.delete(id);
    }
}
