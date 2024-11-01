package com.pooproject.products_system;

import com.pooproject.products_system.dao.ProductDAO;
import com.pooproject.products_system.views.TelaMenu;

public class Main {
    public static void main(String[] args) {
        var dao = new ProductDAO();
        new TelaMenu().setVisible(true);
    }
}
