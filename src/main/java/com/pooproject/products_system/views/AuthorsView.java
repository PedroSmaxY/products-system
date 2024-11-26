package com.pooproject.products_system.views;

import javax.swing.*;
import java.awt.*;

public class AuthorsView extends JFrame {

    public AuthorsView() {
        setTitle("Autores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        panel.add(new JLabel("Pedro Henrique da Silva Novais - Matrícula 1230119539", SwingConstants.CENTER));
        panel.add(new JLabel("Pedro Henrique Sacramento Carvalho - Matrícula 1230114472", SwingConstants.CENTER));
        panel.add(new JLabel("Joao Vitor Canella Rodrigues de Mesquita - Matrícula 1230112636", SwingConstants.CENTER));
        panel.add(new JLabel("Victor Vianna de Souza - Matrícula 1230114650", SwingConstants.CENTER));
        panel.add(new JLabel("Luiz Henrique de Oliveira Duque - Matrícula 1230113418", SwingConstants.CENTER));

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AuthorsView::new);
    }
}



