package com.pooproject.products_system.views;


import com.pooproject.products_system.dao.ProductDAO;
import com.pooproject.products_system.domain.product.Category;
import com.pooproject.products_system.domain.product.Product;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;

public class TelaBanco extends JFrame {
    private DefaultTableModel tableModel;
    private ProductDAO productDAO;

    public TelaBanco() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeTableModel();
        carregarProducts();
    }

    private void initializeTableModel() {
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] {"Nome", "Preço", "Categoria", "Quantidade" });
        jTable1.setModel(tableModel);

        tableModel.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                String columnName = tableModel.getColumnName(column);

                // Obtenha o ID do produto para atualizar
                Long productId = productDAO.findAll().get(row).getId();
                Product produtoAtualizado = productDAO.findById(productId); // Assuma que você tem um método para buscar pelo ID

                switch (column) {
                    case 0:
                        produtoAtualizado.setName((String) tableModel.getValueAt(row, column));
                        break;
                    case 1:
                        produtoAtualizado.setPrice(new BigDecimal(String.valueOf(tableModel.getValueAt(row, column))));
                        break;
                    case 2: // TODO: implementar alteração de categoria!
                        break;
                    case 3:
                        produtoAtualizado.setQuantity(Integer.parseInt((String) tableModel.getValueAt(row, column)));
                        break;
                }

                productDAO.update(produtoAtualizado); // Atualize no banco de dados
            }
        });

    }

    private void carregarProducts() {
        tableModel.setRowCount(0);
        for (Product produto : productDAO.findAll()) {
            tableModel.addRow(new Object[] { produto.getName(), produto.getPrice(), Category.convertCategoryToString(produto.getCategory()), produto.getQuantity() });
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        productDAO = new ProductDAO();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24));
        jLabel1.setText("BANCO DE DADOS");

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(e -> pesquisar());

        jButton2.setText("Voltar");
        jButton2.addActionListener(e -> {
            java.awt.EventQueue.invokeLater(() -> new TelaMenu().setVisible(true));
            dispose();
        });

        jButton3.setText("Deletar");
        jButton3.addActionListener(e ->deletar());

        jTable1.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Nome", "Preço", "Categoria", "Quantidade" }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel2.setText("Pesquisar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1) // Pesquisar
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton3) // Deletar
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2) // Voltar
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1)) // Pesquisar
                                .addGap(18, 18, 18)
                                .addComponent(jButton3) // Deletar
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2) // Voltar
                                .addGap(18, 18, 18))
        );

        pack();
    }

    private void pesquisar() {
        String searchTerm = jTextField1.getText().toLowerCase();
        tableModel.setRowCount(0);
        for (Product produto : productDAO.findAll()) {
            if (produto.getName().toLowerCase().contains(searchTerm)) {
                tableModel.addRow(new Object[] {
                        produto.getName(),
                        produto.getPrice(),
                        Category.convertCategoryToString(produto.getCategory()),
                        produto.getQuantity()
                });
            }
        }
    }

    private void deletar() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Você tem certeza que deseja deletar este item?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Product produtoParaRemover = productDAO.findAll().get(selectedRow);
                productDAO.delete(produtoParaRemover.getId());

                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Product deletado com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para deletar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private javax.swing.JButton jButton1; // Pesquisar
    private javax.swing.JButton jButton2; // Voltar
    private javax.swing.JButton jButton3; // Deletar
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
}
