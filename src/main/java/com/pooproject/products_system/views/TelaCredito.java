package com.pooproject.products_system.views;

import javax.swing.*;
import java.awt.*;

public class TelaCredito extends JFrame {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;

    public TelaCredito() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
        jLabel1.setText("Pedro Henrique da Silva Novais");

        jLabel2.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
        jLabel2.setText("Pedro Henrique Sacramento Carvalho");

        jLabel3.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
        jLabel3.setText("João Vitor Canella Rodrigues de Mesquita");

        jLabel4.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
        jLabel4.setText("Luiz Henrique de Oliveira Duque");

        jLabel5.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
        jLabel5.setText("Victor Vianna de Souza");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
        );

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
        );

        pack();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            TelaCredito tela = new TelaCredito();
            tela.setVisible(true);
        });
    }
}
