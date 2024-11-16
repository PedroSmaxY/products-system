package com.pooproject.products_system.dao;

import com.pooproject.products_system.domain.sale.Sale;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class SaleDAO extends GenericDAO<Sale> {
    public SaleDAO() {
        super(Sale.class);
    }

    public List<Sale> findByDate(LocalDate date) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Sale> query = em.createQuery("SELECT s FROM Sale s WHERE s.currentDate = :date", Sale.class);
            query.setParameter("date", date);
            return query.getResultList();
        }
    }
}
