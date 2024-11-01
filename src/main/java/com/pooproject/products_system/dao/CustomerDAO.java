package com.pooproject.products_system.dao;

import com.pooproject.products_system.domain.customer.Customer;

public class CustomerDAO extends GenericDAO<Customer> {

    public CustomerDAO() {
        super(Customer.class);
    }
}
