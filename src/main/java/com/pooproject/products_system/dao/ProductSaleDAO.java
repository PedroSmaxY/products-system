package com.pooproject.products_system.dao;

import com.pooproject.products_system.domain.productSale.ProductSale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductSaleDAO extends GenericDAO<ProductSale> {
    public ProductSaleDAO() {
        super(ProductSale.class);
    }

    public List<ProductSale> findByProductId(Long productId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ProductSale> query = em.createQuery(
                    "SELECT ps FROM ProductSale ps WHERE ps.product.id = :productId", ProductSale.class);
            query.setParameter("productId", productId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
