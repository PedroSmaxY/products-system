package com.pooproject.products_system.dao;

import com.pooproject.products_system.domain.sale.Sale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class SaleDAO extends GenericDAO<Sale> {
    public SaleDAO() {
        super(Sale.class);
    }

    public List<Sale> findByDate(LocalDate date) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Sale> query = em.createQuery("SELECT s FROM Sale s WHERE s.currentDate = :date", Sale.class);
            query.setParameter("date", date);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
