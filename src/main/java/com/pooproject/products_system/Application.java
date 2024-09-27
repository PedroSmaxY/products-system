package com.pooproject.products_system;

import com.pooproject.products_system.dao.ProductDAO;
import com.pooproject.products_system.models.Product;
import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        var dao = new ProductDAO();

        dao.save(new Product(null, "macarrão", "macarrão barato", new BigDecimal("6.7"), 2));

        var products = dao.findAll();
        products.forEach(System.out::println);
    }
}
