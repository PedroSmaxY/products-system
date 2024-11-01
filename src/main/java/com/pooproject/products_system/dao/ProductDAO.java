package com.pooproject.products_system.dao;

import com.pooproject.products_system.domain.product.Product;


public class ProductDAO extends GenericDAO<Product> {
    public ProductDAO(Class<Product> entityClass) {
        super(Product.class);
    }
}
