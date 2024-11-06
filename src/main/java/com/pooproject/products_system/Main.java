package com.pooproject.products_system;

import com.pooproject.products_system.dao.ProductDAO;
import com.pooproject.products_system.domain.category.Category;
import com.pooproject.products_system.domain.product.Product;
import com.pooproject.products_system.services.CategoryService;
import com.pooproject.products_system.views.MainView;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        var dao = new ProductDAO();
        new MainView().setVisible(true);
    }
}
