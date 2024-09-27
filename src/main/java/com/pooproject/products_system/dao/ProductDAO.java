package com.pooproject.products_system.dao;

import com.pooproject.products_system.exceptions.ProductDAOException;
import com.pooproject.products_system.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProductDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

    public void save(Product product) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new ProductDAOException("Failed to save product", e);
        }
    }

    public Product findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Product.class, id);
        } catch (Exception e) {
            throw new ProductDAOException("Failed to find product with id: " + id, e);
        }
    }

    public void update(Product product) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new ProductDAOException("Failed to update product", e);
        }
    }

    public void delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.getTransaction().begin();
                em.remove(product);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            throw new ProductDAOException("Failed to delete product with id: " + id, e);
        }
    }

    public List<Product> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("from Product", Product.class).getResultList();
        } catch (Exception e) {
            throw new ProductDAOException("Failed to retrieve product list", e);
        }
    }
}
