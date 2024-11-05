/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pooproject.products_system.views;

import com.pooproject.products_system.domain.category.Category;
import com.pooproject.products_system.domain.product.Product;
import com.pooproject.products_system.dto.ProductDTO;
import com.pooproject.products_system.services.CategoryService;
import com.pooproject.products_system.services.ProductService;
import com.pooproject.products_system.views.tableModels.ProductTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.swing.*;

/**
 *
 * @author Pedro Henrique
 */
public class ProductView extends javax.swing.JFrame {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductTableModel model;
    
    /**
     * Creates new form ProductView
     */
    public ProductView() {
        // Initialize services
        productService = new ProductService();
        categoryService = new CategoryService();

        // Load products and categories
        List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();

        // Initialize model
        model = new ProductTableModel(products);

        // Initialize UI components
        initComponents();

        // Set the model for the JTable
        jTableProduct.setModel(model);

        // Setup table listener
        setupTableListener();
    }
    
    private void setupTableListener() {
        jTableProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jTableProduct.rowAtPoint(e.getPoint());
                int column = jTableProduct.columnAtPoint(e.getPoint());

                if (column == 5) { // Editar
                    editProduct(productService.findAll().get(row));
                } else if (column == 6) { // Remover
                    removeProduct(row);
                }
            }
        });
    }

    private void editProduct(Product product) {
        JDialog dialog = new JDialog(this, "Editar Produto", true);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField(product.getName());
        JTextField priceField = new JTextField(product.getPrice().toString());
        JTextField quantityField = new JTextField(product.getQuantity().toString());
        JComboBox<String> categoryField = new JComboBox<>();
        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("");
        categoryNames.addAll(categoryService.findAll().stream().map(Category::getName).toList());
        categoryField.setModel(new javax.swing.DefaultComboBoxModel<>(categoryNames.toArray(new String[0])));
        categoryField.setSelectedItem(product.getCategory() != null ? product.getCategory().getName() : "");

        dialog.add(new JLabel("Nome:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Preço:"));
        dialog.add(priceField);
        dialog.add(new JLabel("Quantidade:"));
        dialog.add(quantityField);
        dialog.add(new JLabel("Categoria:"));
        dialog.add(categoryField);

        JButton saveButton = new JButton("Salvar");
        saveButton.addActionListener(e -> {
            String newName = nameField.getText().trim();
            String newPrice = priceField.getText().trim();
            String newQuantity = quantityField.getText().trim();

            String selectedCategoryName = categoryField.getSelectedItem() != null ? categoryField.getSelectedItem().toString() : null;

            Optional<Category> newCategory = Optional.empty();

            if (selectedCategoryName != null && !selectedCategoryName.isEmpty()) {
                newCategory = categoryService.findByName(selectedCategoryName);
            }

            if (!newName.isEmpty()) {
                product.setName(newName);
            }
            if (!newPrice.isEmpty()) {
                product.setPrice(new BigDecimal(newPrice));
            }
            if (!newQuantity.isEmpty()) {
                product.setQuantity(Integer.parseInt(newQuantity));
            }
            newCategory.ifPresent(product::setCategory);

            dialog.dispose();
            productService.update(product);
            updateProductTable();
        });

        dialog.add(saveButton);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void removeProduct(int rowIndex) {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Tem certeza que deseja remover este cliente?", 
            "Confirmar Remoção", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            productService.delete(model.getProductAt(rowIndex).getId());
            updateProductTable();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProduct = new javax.swing.JTable();
        jLabelTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextName = new javax.swing.JTextField();
        jTextPrice = new javax.swing.JTextField();
        jTextQuantity = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton();
        jTextSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Produtos");
        setResizable(false);

        jTableProduct.setModel(model);
        jTableProduct.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jScrollPane1.setViewportView(jTableProduct);

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTitle.setText("PRODUTOS");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nome:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Preço:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Quantidade:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Categoria:");

        jTextName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTextPrice.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTextQuantity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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

        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("----------");
        categoryNames.addAll(categoryService.findAll().stream().map(Category::getName).toList());
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(categoryNames.toArray(new String[0])));
        jComboBox1.setSelectedItem("----------");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(jLabelTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
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
                                    .addComponent(jTextName, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                    .addComponent(jTextPrice)
                                    .addComponent(jTextQuantity)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jButtonSearch)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
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
                            .addComponent(jTextPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSave)))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        try {
            var name = jTextName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome do produto não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            var priceString = jTextPrice.getText().trim();
            if (priceString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O preço do produto não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            var price = new BigDecimal(priceString);

            var quantityString = jTextQuantity.getText().trim();
            if (quantityString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "A quantidade do produto não pode estar vazia.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            var quantity = Integer.valueOf(quantityString);

            var category = (String) jComboBox1.getSelectedItem();
            if (category == null || category.isEmpty()) {
                JOptionPane.showMessageDialog(this, "A categoria do produto não pode estar vazia.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            var optionalCategory = categoryService.findByName(category);
            if (optionalCategory.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Categoria não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            jTextName.setText("");
            jTextPrice.setText("");
            jTextQuantity.setText("");
            jComboBox1.setSelectedIndex(0);
            var newProduct = new ProductDTO(name, price, quantity, optionalCategory.get());
            productService.save(new Product(newProduct));
            updateProductTable();
            JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço ou quantidade inválidos. Por favor, insira valores numéricos válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar o produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void updateProductTable() {
        // Fetch the updated product list
        List<Product> updatedProducts = productService.findAll();

        // Update the model with the new list
        model.setProducts(updatedProducts);

        // Notify the table that the data has changed
        model.fireTableDataChanged();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProduct;
    private javax.swing.JTextField jTextName;
    private javax.swing.JTextField jTextPrice;
    private javax.swing.JTextField jTextQuantity;
    private javax.swing.JTextField jTextSearch;
    // End of variables declaration//GEN-END:variables
}
