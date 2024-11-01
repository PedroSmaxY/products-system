package com.pooproject.products_system.dao;

import com.pooproject.products_system.domain.category.Category;

public class CategoryDAO extends GenericDAO<Category> {
    public CategoryDAO() {
        super(Category.class);
    }
}
