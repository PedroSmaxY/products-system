package com.pooproject.products_system.dao;

import com.pooproject.products_system.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.List;

public class ProductDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

    @Transactional
    public void save(Product product) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public Product findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();
        return product;
    }

    @Transactional
    public void update(Product product) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.getTransaction().begin();
            em.remove(product);
            em.getTransaction().commit();
        }
        em.close();
    }

    public List<Product> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Product> products = em.createQuery("from Product", Product.class).getResultList();
        em.close();
        return products;
    }
}