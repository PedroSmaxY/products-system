package com.pooproject.products_system.services;

import com.pooproject.products_system.domain.customer.Customer;
import com.pooproject.products_system.dao.CustomerDAO;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer save(Customer customer) {
        return customerDAO.save(customer);
    }

    public Optional<Customer> findById(Long id) {
        Customer customer = customerDAO.findById(id);
        return Optional.ofNullable(customer);
    }

    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    public Customer update(Customer customer) {
        return customerDAO.update(customer);
    }

    public void delete(Long id) {
        customerDAO.delete(id);
    }
}
