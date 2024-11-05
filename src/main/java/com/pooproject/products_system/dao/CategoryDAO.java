package com.pooproject.products_system.dao;

import com.pooproject.products_system.domain.category.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CategoryDAO extends GenericDAO<Category> {
    public CategoryDAO() {
        super(Category.class);
    }

    public Category findByName(String name) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
