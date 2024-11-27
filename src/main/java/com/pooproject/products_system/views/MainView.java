/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pooproject.products_system.views;

import javax.swing.*;

public class MainView extends javax.swing.JFrame {

    public MainView() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jLabelImage = new javax.swing.JLabel();
        jButtonCustomer = new javax.swing.JButton();
        jButtonProduct = new javax.swing.JButton();
        jButtonSale = new javax.swing.JButton();
        jButtonSaleHistory = new javax.swing.JButton();
        jButtonAuthors = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Inicial");
        setResizable(false);

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelTitle.setText("MERCADINHO");

        jLabelImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/static/images/produtos.png")));

        jButtonCustomer.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jButtonCustomer.setText("Clientes");
        jButtonCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCustomerActionPerformed(evt);
            }
        });

        jButtonProduct.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jButtonProduct.setText("Produtos");
        jButtonProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProductActionPerformed(evt);
            }
        });

        jButtonSale.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jButtonSale.setText("Vendas");
        jButtonSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { jButtonSaleActionPerformed(evt); }
        });

        jButtonSaleHistory.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jButtonSaleHistory.setText("Histórico de Vendas");
        jButtonSaleHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { jButtonSaleHistoryActionPerformed(evt); }
        });

        jButtonAuthors = new javax.swing.JButton();

        jButtonAuthors.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jButtonAuthors.setText("Sobre");
        jButtonAuthors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { jButtonAuthors(evt); }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(237, Short.MAX_VALUE)
                                .addComponent(jLabelTitle)
                                .addGap(236, 236, 236))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(213, 213, 213)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jButtonProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonSaleHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonAuthors, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(139, 139, 139)
                                                .addComponent(jLabelImage)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabelTitle)
                                .addGap(53, 53, 53)
                                .addComponent(jLabelImage)
                                .addGap(56, 56, 56)
                                .addComponent(jButtonCustomer)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonProduct)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSale)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSaleHistory)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAuthors)
                                .addGap(18, 18, 18))
        );

        pack();
    }

    private void jButtonSaleHistoryActionPerformed(java.awt.event.ActionEvent evt) {
        new SaleHistoryView().setVisible(true);
    }

    private void jButtonSaleActionPerformed(java.awt.event.ActionEvent evt) {
        SwingUtilities.invokeLater(SaleView::new);
    }

    private void jButtonCustomerActionPerformed(java.awt.event.ActionEvent evt) {
        new CustomerView().setVisible(true);
    }

    private void jButtonProductActionPerformed(java.awt.event.ActionEvent evt) {
        new ProductView().setVisible(true);
    }

    private void jButtonAuthors(java.awt.event.ActionEvent evt) {
        new AboutView().setVisible(true);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButtonCustomer;
    private javax.swing.JButton jButtonProduct;
    private javax.swing.JButton jButtonSale;
    private javax.swing.JButton jButtonSaleHistory;
    private javax.swing.JLabel jLabelImage;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JButton jButtonAuthors;
}
