package com.pooproject.products_system;

import com.pooproject.products_system.views.MainView;


public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainView().setVisible(true);
        });
    }
}
