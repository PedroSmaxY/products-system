package com.pooproject.products_system.views;

import javax.swing.*;
import java.net.URL;

public class TelaMenu extends JFrame {

    public TelaMenu() {
        initComponents();
        setLocationRelativeTo( null );
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        bcadastro = new javax.swing.JButton();
        bsair = new javax.swing.JButton();
        bbancodedados = new javax.swing.JButton();
        imagemLabel2 = new javax.swing.JLabel();
        bcreditos = new javax.swing.JButton();

        URL imagemUrl = TelaMenu.class.getClassLoader().getResource("static/images/produtos.png");

        assert imagemUrl != null;
        ImageIcon icon = new ImageIcon(imagemUrl);
        imagemLabel2.setIcon(icon);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24));
        jLabel1.setText("SISTEMA DE PRODUTOS");

        bcadastro.setText("Cadastro");
        bcadastro.addActionListener(this::bcadastroActionPerformed);

        bsair.setText("Sair");
        bsair.addActionListener(e -> {
            System.exit(0);
        });

        bbancodedados.setText("Banco de Dados");
        bbancodedados.addActionListener(this::bbancodedadosActionPerformed);
        
        bcreditos.setText("Créditos");
        bcreditos.addActionListener(this::bcreditoActionPerformed);
                        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(70, 70, 100)
                                                .addComponent(imagemLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(82, Short.MAX_VALUE)
                                                .addComponent(jLabel1)))
                                .addGap(79, 79, 79))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(bcadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bbancodedados, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bsair, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bcreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addComponent(imagemLabel2)
                                .addGap(35, 35, 35)
                                .addComponent(bcadastro)
                                .addGap(18, 18, 18)
                                .addComponent(bbancodedados)
                                .addGap(18, 18, 18)
                                .addComponent(bsair)
                                .addGap(18, 18, 18)
                                .addComponent(bcreditos)
                                .addGap(18, 18, 18))
        );

        pack();
    }

    private void bcadastroActionPerformed(java.awt.event.ActionEvent evt) {
        TelaCadastro telaCadastro = new TelaCadastro();
            telaCadastro.setVisible(true);
            dispose();
    }

    private void bbancodedadosActionPerformed(java.awt.event.ActionEvent evt) {
        TelaBanco telaBanco= new TelaBanco();
            telaBanco.setVisible(true);
            dispose();
    }
    
    private void bcreditoActionPerformed(java.awt.event.ActionEvent evt) {
        TelaCredito telacredito = new TelaCredito();
        telacredito.setVisible(true);
    }

    private javax.swing.JButton bcadastro;
    private javax.swing.JButton bsair;
    private javax.swing.JButton bbancodedados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel imagemLabel2;
    private javax.swing.JButton bcreditos;
}