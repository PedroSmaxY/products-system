package com.pooproject.products_system.dao;

import com.pooproject.products_system.domain.customer.Customer;
import jakarta.persistence.EntityManager;
import java.util.List;

public class CustomerDAO extends GenericDAO<Customer> {

    public CustomerDAO() {
        super(Customer.class);
    }
    
    public List<Customer> findByNameContaining(String name) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE :name", Customer.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Customer findCustomerWithSales(Long customerId) {
        EntityManager em = getEntityManager();
        Customer customer = em.find(Customer.class, customerId);
        if (customer != null) {
            customer.getSales().size();
        }
        return customer;
    }

}
