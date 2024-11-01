package com.pooproject.products_system.services;

import com.pooproject.products_system.domain.product.Product;
import com.pooproject.products_system.dao.ProductDAO;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product save(Product product) {
        return productDAO.save(product);
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productDAO.findById(id));
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Product update(Product product) {
        return productDAO.update(product);
    }

    public void delete(Long id) {
        productDAO.delete(id);
    }
}
