package com.pooproject.products_system.views;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleHistoryView extends JFrame {

    private JTable jTableSales;
    private JButton jButtonViewHistory;
    private SaleTableModel saleTableModel;

    public SaleHistoryView() {
        initComponents();
    }

    private void initComponents() {
        JLabel jLabelTitle = new JLabel("Histórico de Vendas", JLabel.CENTER);
        jLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));

        saleTableModel = new SaleTableModel();
        jTableSales = new JTable(saleTableModel);

        // Adicionando dados de exemplo
        saleTableModel.addSale(new Sale(1L, "Cliente Exemplo"));
        saleTableModel.addSale(new Sale(2L, "Cliente Exemplo 2"));

        JScrollPane jScrollPane = new JScrollPane(jTableSales);

        jButtonViewHistory = new JButton("Ver Histórico");
        jButtonViewHistory.addActionListener(e -> showCustomerHistory());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                        .addComponent(jButtonViewHistory, GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(30)
                        .addComponent(jLabelTitle)
                        .addGap(30)
                        .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addComponent(jButtonViewHistory)
                        .addGap(20)
        );

        pack();
        setLocationRelativeTo(null);
        setTitle("Histórico de Vendas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void showCustomerHistory() {
        int selectedRow = jTableSales.getSelectedRow();
        if (selectedRow >= 0) {
            Sale selectedSale = saleTableModel.getSaleAt(selectedRow);
            new CustomerHistoryView(selectedSale).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma venda para ver o histórico.");
        }
    }

    private class SaleTableModel extends AbstractTableModel {

        private final String[] columnNames = {"ID", "Cliente", "Deletar"};
        private final List<Sale> sales = new ArrayList<>();

        @Override
        public int getRowCount() {
            return sales.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Sale sale = sales.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return sale.getId();
                case 1:
                    return sale.getCustomer();
                default:
                    return "🗑️";  // Emoji de lixeira na coluna "Deletar"
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        public void addSale(Sale sale) {
            sales.add(sale);
            fireTableDataChanged();
        }

        public Sale getSaleAt(int rowIndex) {
            return sales.get(rowIndex);
        }
    }

    public static class Sale {
        private Long id;
        private String customer;

        public Sale(Long id, String customer) {
            this.id = id;
            this.customer = customer;
        }

        public Long getId() {
            return id;
        }

        public String getCustomer() {
            return customer;
        }
    }

    // Classe para exibir o histórico de compras do cliente
    private static class CustomerHistoryView extends JFrame {
        private JTable jTablePurchaseHistory;
        private JButton jButtonViewDetails;
        private PurchaseHistoryTableModel purchaseHistoryTableModel;

        public CustomerHistoryView(Sale sale) {
            setTitle("Histórico de Compras - Cliente: " + sale.getCustomer());
            setSize(500, 300);
            setLocationRelativeTo(null);

            JLabel jLabelTitle = new JLabel("Histórico de Compras", JLabel.CENTER);
            jLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));

            purchaseHistoryTableModel = new PurchaseHistoryTableModel();
            jTablePurchaseHistory = new JTable(purchaseHistoryTableModel);

            // Exemplo de compras do cliente
            purchaseHistoryTableModel.addPurchase(new Purchase("2023-11-10", new BigDecimal("250.00")));
            purchaseHistoryTableModel.addPurchase(new Purchase("2023-11-11", new BigDecimal("180.00")));

            JScrollPane jScrollPane = new JScrollPane(jTablePurchaseHistory);

            jButtonViewDetails = new JButton("Ver Detalhes");
            jButtonViewDetails.addActionListener(e -> showPurchaseDetails());

            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);

            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                                            .addComponent(jButtonViewDetails, GroupLayout.Alignment.TRAILING))
                                    .addContainerGap())
            );

            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGap(20)
                            .addComponent(jLabelTitle)
                            .addGap(20)
                            .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(jButtonViewDetails)
                            .addGap(20)
            );
        }

        private void showPurchaseDetails() {
            int selectedRow = jTablePurchaseHistory.getSelectedRow();
            if (selectedRow >= 0) {
                Purchase selectedPurchase = purchaseHistoryTableModel.getPurchaseAt(selectedRow);
                new PurchaseDetailsView(selectedPurchase).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma compra para ver os detalhes.");
            }
        }

        private class PurchaseHistoryTableModel extends AbstractTableModel {

            private final String[] columnNames = {"Data", "Total", "Deletar"};
            private final List<Purchase> purchases = new ArrayList<>();

            @Override
            public int getRowCount() {
                return purchases.size();
            }

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Purchase purchase = purchases.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return purchase.getDate();
                    case 1:
                        return "R$ " + purchase.getTotal();
                    default:
                        return "🗑️";  // Emoji de lixeira na coluna "Deletar"
                }
            }

            @Override
            public String getColumnName(int column) {
                return columnNames[column];
            }

            public void addPurchase(Purchase purchase) {
                purchases.add(purchase);
                fireTableDataChanged();
            }

            public Purchase getPurchaseAt(int rowIndex) {
                return purchases.get(rowIndex);
            }
        }

        private static class Purchase {
            private String date;
            private BigDecimal total;

            public Purchase(String date, BigDecimal total) {
                this.date = date;
                this.total = total;
            }

            public String getDate() {
                return date;
            }

            public BigDecimal getTotal() {
                return total;
            }
        }
    }

    // Classe para exibir os detalhes da compra
    private static class PurchaseDetailsView extends JFrame {
        private JTable jTableProductDetails;
        private ProductDetailsTableModel productDetailsTableModel;

        public PurchaseDetailsView(CustomerHistoryView.Purchase purchase) {
            setTitle("Detalhes da Compra - Data: " + purchase.getDate());
            setSize(500, 300);
            setLocationRelativeTo(null);

            JLabel jLabelTitle = new JLabel("Detalhes da Compra", JLabel.CENTER);
            jLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));

            productDetailsTableModel = new ProductDetailsTableModel();
            jTableProductDetails = new JTable(productDetailsTableModel);

            // Exemplo de produtos comprados
            productDetailsTableModel.addProduct(new Product("Produto 1", 2, new BigDecimal("50.00")));
            productDetailsTableModel.addProduct(new Product("Produto 2", 1, new BigDecimal("100.00")));

            JScrollPane jScrollPane = new JScrollPane(jTableProductDetails);

            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);

            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                                    .addContainerGap())
            );

            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGap(20)
                            .addComponent(jLabelTitle)
                            .addGap(20)
                            .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addGap(20)
            );
        }

        private class ProductDetailsTableModel extends AbstractTableModel {

            private final String[] columnNames = {"Produto", "Quantidade", "Valor Unitário", "Total"};
            private final List<Product> products = new ArrayList<>();

            @Override
            public int getRowCount() {
                return products.size();
            }

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Product product = products.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return product.getName();
                    case 1:
                        return product.getQuantity();
                    case 2:
                        return "R$ " + product.getUnitPrice();
                    case 3:
                        return "R$ " + product.getTotal();
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                return columnNames[column];
            }

            public void addProduct(Product product) {
                products.add(product);
                fireTableDataChanged();
            }
        }

        private static class Product {
            private String name;
            private int quantity;
            private BigDecimal unitPrice;

            public Product(String name, int quantity, BigDecimal unitPrice) {
                this.name = name;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
            }

            public String getName() {
                return name;
            }

            public int getQuantity() {
                return quantity;
            }

            public BigDecimal getUnitPrice() {
                return unitPrice;
            }

            public BigDecimal getTotal() {
                return unitPrice.multiply(BigDecimal.valueOf(quantity));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SaleHistoryView().setVisible(true));
    }
}
