package com.pooproject.products_system.dao;

import com.pooproject.products_system.domain.productSale.ProductSale;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ProductSaleDAO extends GenericDAO<ProductSale> {
    public ProductSaleDAO() {
        super(ProductSale.class);
    }

    public List<ProductSale> findByProductId(Long productId) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<ProductSale> query = em.createQuery(
                    "SELECT ps FROM ProductSale ps WHERE ps.product.id = :productId", ProductSale.class);
            query.setParameter("productId", productId);
            return query.getResultList();
        }
    }
}
