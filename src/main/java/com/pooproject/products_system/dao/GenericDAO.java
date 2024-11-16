package com.pooproject.products_system.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class GenericDAO<T> {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    private final Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public T save(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;  // rethrow exception after rollback
        } finally {
            em.close();
        }
    }

    public T update(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T mergedEntity = em.merge(entity);
            em.getTransaction().commit();
            return mergedEntity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public T findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
        } finally {
            em.close();
        }
    }

    public static void closeEntityManagerFactory() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
