/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pooproject.products_system.views;

import com.pooproject.products_system.domain.customer.Customer;
import com.pooproject.products_system.dto.CustomerDTO;
import com.pooproject.products_system.services.CustomerService;
import com.pooproject.products_system.views.tableModels.CustomerTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;
import javax.swing.*;

public class CustomerView extends javax.swing.JFrame {

    private CustomerService customerService;
    private CustomerTableModel model;
    private List<Customer> customers;

    public CustomerView() {
        customerService = new CustomerService();
        customers = customerService.findAll();
        model = new CustomerTableModel(customers);

        initComponents();

        jTableCustomer.setModel(model);
        setupTableListener();
    }
    
    private void setupTableListener() {
        jTableCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jTableCustomer.rowAtPoint(e.getPoint());
                int column = jTableCustomer.columnAtPoint(e.getPoint());

                if (column == 5) { // Editar
                    editCustomer(customers.get(row));
                } else if (column == 6) {
                    removeCustomer(row);
                }
            }
        });
    }

    private void editCustomer(Customer customer) {
        JDialog dialog = new JDialog(this, "Editar Cliente", true);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField(customer.getName());
        JTextField addressField = new JTextField(customer.getAddress());
        JTextField phoneField = new JTextField(customer.getPhone());
        JTextField emailField = new JTextField(customer.getEmail());

        dialog.add(new JLabel("Nome:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Endereço:"));
        dialog.add(addressField);
        dialog.add(new JLabel("Telefone:"));
        dialog.add(phoneField);
        dialog.add(new JLabel("E-mail:"));
        dialog.add(emailField);

        JButton saveButton = new JButton("Salvar");
        saveButton.addActionListener(e -> {
            String newName = nameField.getText().trim();
            String newAddress = addressField.getText().trim();
            String newPhone = phoneField.getText().trim();
            String newEmail = emailField.getText().trim();

            if (!newName.isEmpty()) {
                customer.setName(newName);
            }
            if (!newAddress.isEmpty()) {
                customer.setAddress(newAddress);
            }
            if (!newPhone.isEmpty()) {
                customer.setPhone(newPhone);
            }
            if (!newEmail.isEmpty()) {
                customer.setEmail(newEmail);
            }

            model.fireTableDataChanged();
            customerService.update(customer);
            dialog.dispose();
        });

        dialog.add(saveButton);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void removeCustomer(int rowIndex) {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Tem certeza que deseja remover este cliente?", 
            "Confirmar Remoção", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            customerService.delete(model.getCustomerAt(rowIndex).getId());
            model.removeCustomer(rowIndex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCustomer = new javax.swing.JTable();
        jLabelTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextName = new javax.swing.JTextField();
        jTextAddress = new javax.swing.JTextField();
        jTextPhone = new javax.swing.JTextField();
        jTextEmail = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton();
        jTextSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu Clientes");
        setResizable(false);

        jTableCustomer.setModel(model);
        jScrollPane1.setViewportView(jTableCustomer);

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTitle.setText("CLIENTES");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nome:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Endereço:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Telefone:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("E-mail:");

        jTextName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTextAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTextPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTextEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButtonSave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonSave.setText("Salvar");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jTextSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButtonSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonSearch.setText("Buscar");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonSave)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextName)
                                    .addComponent(jTextAddress)
                                    .addComponent(jTextPhone)
                                    .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jButtonSearch)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelTitle)
                .addGap(309, 309, 309))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSearch, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSave)))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {                                            
        var name = jTextName.getText().trim();
        var address = jTextAddress.getText().trim();
        var phone = jTextPhone.getText().trim();
        var email = jTextEmail.getText().trim();

        if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos precisam ser preenchidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        var newCustomerDTO = new CustomerDTO(name, address, phone, email);
        customerService.save(new Customer(newCustomerDTO));

        updateCustomerTable();

    }                                           

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {
        String searchText = jTextSearch.getText().trim();

        if (searchText.isEmpty()) {
            updateCustomerTable();
        } else {
            List<Customer> filteredCustomers = customerService.findByNameContaining(searchText);
            model.setCustomers(filteredCustomers);
            model.fireTableDataChanged();
        }
    }

    private void updateCustomerTable() {
        List<Customer> updatedCustomer = customerService.findAll();

        model.setCustomers(updatedCustomer);

        model.fireTableDataChanged();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerView().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCustomer;
    private javax.swing.JTextField jTextAddress;
    private javax.swing.JTextField jTextEmail;
    private javax.swing.JTextField jTextName;
    private javax.swing.JTextField jTextPhone;
    private javax.swing.JTextField jTextSearch;
}
