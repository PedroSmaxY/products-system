package com.pooproject.products_system.dao;

import com.pooproject.products_system.domain.product.Product;
import jakarta.persistence.EntityManager;
import java.util.List;


public class ProductDAO extends GenericDAO<Product> {
    public ProductDAO() {
        super(Product.class);
    }
    
    public List<Product> findByNameContaining(String name) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name", Product.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
