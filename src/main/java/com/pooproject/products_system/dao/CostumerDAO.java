package com.pooproject.products_system.dao;

import com.pooproject.products_system.domain.costumer.Costumer;

public class CostumerDAO extends GenericDAO<Costumer> {

    public CostumerDAO(Class<Costumer> entityClass) {
        super(Costumer.class);
    }
}
