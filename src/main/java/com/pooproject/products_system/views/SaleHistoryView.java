package com.pooproject.products_system.views;

import com.pooproject.products_system.domain.customer.Customer;
import com.pooproject.products_system.domain.sale.Sale;
import com.pooproject.products_system.services.CustomerService;
import com.pooproject.products_system.services.SaleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SaleHistoryView extends JFrame {

    private JTable jTableSales;
    private List<Sale> sales;
    private List<Sale> filteredSales;
    private JButton jButtonViewHistory;
    private SaleTableModel saleTableModel;
    private SaleService saleService;
    private CustomerService customerService;

    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField customerField;
    private JButton searchButton;
    private JButton exportButton;

    public SaleHistoryView() {
        saleService = new SaleService();
        customerService = new CustomerService();
        sales = this.saleService.findAllSales();
        filteredSales = new ArrayList<>(sales);
        initComponents();
    }

    private void initComponents() {
        JLabel jLabelTitle = new JLabel("Histórico de Vendas", JLabel.CENTER);
        jLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));

        saleTableModel = new SaleTableModel();
        jTableSales = new JTable(saleTableModel);

        // Adicionando ordenação
        TableRowSorter<SaleTableModel> sorter = new TableRowSorter<>(saleTableModel);
        jTableSales.setRowSorter(sorter);

        JScrollPane jScrollPane = new JScrollPane(jTableSales);

        // Adicionando os campos de data e cliente
        JLabel startDateLabel = new JLabel("Data Início:");
        JLabel endDateLabel = new JLabel("Data Fim:");
        JLabel customerLabel = new JLabel("Cliente:");

        startDateField = new JTextField(10);
        endDateField = new JTextField(10);
        customerField = new JTextField(10);

        searchButton = new JButton("Buscar");
        searchButton.setToolTipText("Filtrar vendas por datas e cliente");
        searchButton.addActionListener(e -> searchSales());

        exportButton = new JButton("Exportar para CSV");
        exportButton.setToolTipText("Exportar vendas filtradas para um arquivo CSV");
        exportButton.addActionListener(e -> exportToCSV());

        jButtonViewHistory = new JButton("Ver Histórico");
        jButtonViewHistory.setToolTipText("Visualizar histórico detalhado do cliente");
        jButtonViewHistory.addActionListener(e -> showCustomerHistory());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(startDateLabel)
                                        .addComponent(startDateField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(endDateLabel)
                                        .addComponent(endDateField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(customerLabel)
                                        .addComponent(customerField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(exportButton)
                                        .addGap(10)
                                        .addComponent(jButtonViewHistory)))
                        .addContainerGap()
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(30)
                        .addComponent(jLabelTitle)
                        .addGap(30)
                        .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(startDateLabel)
                                .addComponent(startDateField)
                                .addComponent(endDateLabel)
                                .addComponent(endDateField)
                                .addComponent(customerLabel)
                                .addComponent(customerField)
                                .addComponent(searchButton)
                        )
                        .addGap(18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(exportButton)
                                .addComponent(jButtonViewHistory))
                        .addGap(20)
        );

        pack();
        setLocationRelativeTo(null);
        setTitle("Histórico de Vendas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void searchSales() {
        try {
            String startDateText = startDateField.getText();
            String endDateText = endDateField.getText();
            String customerName = customerField.getText().toLowerCase();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate startDate = startDateText.isEmpty() ? null : LocalDate.parse(startDateText, formatter);
            LocalDate endDate = endDateText.isEmpty() ? null : LocalDate.parse(endDateText, formatter);

            filteredSales.clear();

            for (Sale sale : sales) {
                LocalDate saleDate = sale.getCurrentDate();
                boolean matchesDate = (startDate == null || !saleDate.isBefore(startDate)) && (endDate == null || !saleDate.isAfter(endDate));
                boolean matchesCustomer = customerName.isEmpty() || sale.getCustomer().getName().toLowerCase().contains(customerName);

                if (matchesDate && matchesCustomer) {
                    filteredSales.add(sale);
                }
            }

            saleTableModel.fireTableDataChanged();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data inválida. Use o formato dd/MM/yyyy.");
        }
    }

    private void exportToCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar como...");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter writer = new PrintWriter(fileChooser.getSelectedFile() + ".csv")) {
                writer.println("ID,Cliente,Data");
                for (Sale sale : filteredSales) {
                    writer.printf("%d,%s,%s%n",
                            sale.getId(),
                            sale.getCustomer().getName(),
                            sale.getCurrentDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    );
                }
                JOptionPane.showMessageDialog(this, "Exportado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao exportar: " + ex.getMessage());
            }
        }
    }

    private void showCustomerHistory() {
        int selectedRow = jTableSales.getSelectedRow();
        if (selectedRow >= 0) {
            Sale selectedSale = saleTableModel.getSaleAt(selectedRow);

            Customer customer = customerService.findCustomerWithSales(selectedSale.getCustomer().getId());

            if (customer != null) {
                new CustomerHistoryView(selectedSale).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível carregar o histórico do cliente.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma venda para ver o histórico.");
        }
    }


    private class SaleTableModel extends AbstractTableModel {

        private final String[] columnNames = {"ID", "Cliente", "Data"};

        @Override
        public int getRowCount() {
            return filteredSales.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Sale sale = filteredSales.get(rowIndex);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            switch (columnIndex) {
                case 0:
                    return sale.getId();
                case 1:
                    return sale.getCustomer().getName();
                case 2:
                    LocalDate saleDate = sale.getCurrentDate();
                    return saleDate != null ? saleDate.format(formatter) : "Data inválida";
                default:
                    return "";
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        public Sale getSaleAt(int rowIndex) {
            return filteredSales.get(rowIndex);
        }
    }

    public class CustomerHistoryView extends JFrame {

        private JTable jTablePurchaseHistory;
        private PurchaseHistoryTableModel purchaseHistoryTableModel;

        public CustomerHistoryView(Sale sale) {
            setTitle("Histórico de Vendas - Cliente: " + sale.getCustomer().getName());
            setSize(500, 300);
            setLocationRelativeTo(null);

            JLabel jLabelTitle = new JLabel("Histórico de Vendas", JLabel.CENTER);
            jLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));

            purchaseHistoryTableModel = new PurchaseHistoryTableModel();
            jTablePurchaseHistory = new JTable(purchaseHistoryTableModel);

            // Carregar as compras da venda selecionada
            sale.getProductSales().forEach(productSale ->
                    purchaseHistoryTableModel.addPurchase(new Purchase(
                            productSale.getProduct().getName(),
                            productSale.getQuantity(),
                            productSale.getPrice())
                    )
            );

            JScrollPane jScrollPane = new JScrollPane(jTablePurchaseHistory);



            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);

            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                                    .addContainerGap())
            );

            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGap(20)
                            .addComponent(jLabelTitle)
                            .addGap(20)
                            .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
            );
        }

        private static class PurchaseHistoryTableModel extends AbstractTableModel {

            private final String[] columnNames = {"Produto", "Quantidade", "Preço Unitário", "Total"};
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
                        return purchase.getProductName();
                    case 1:
                        return purchase.getQuantity();
                    case 2:
                        return purchase.getPrice().setScale(2);
                    case 3:
                        return purchase.getTotal().setScale(2);
                    default:
                        return "";
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

        public static class PurchaseDetailsView extends JFrame {

            public PurchaseDetailsView(Purchase purchase) {
                setTitle("Detalhes da Venda");
                setSize(400, 300);
                setLocationRelativeTo(null);

                JLabel jLabelTitle = new JLabel("Detalhes da Venda", JLabel.CENTER);
                jLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(4, 2, 10, 10));

                panel.add(new JLabel("Produto:"));
                panel.add(new JLabel(purchase.getProductName()));

                panel.add(new JLabel("Quantidade:"));
                panel.add(new JLabel(String.valueOf(purchase.getQuantity())));

                panel.add(new JLabel("Preço Unitário:"));
                panel.add(new JLabel(purchase.getPrice().setScale(2).toString()));

                panel.add(new JLabel("Preço Total:"));
                panel.add(new JLabel(purchase.getTotal().setScale(2).toString()));

                getContentPane().add(jLabelTitle, BorderLayout.NORTH);
                getContentPane().add(panel, BorderLayout.CENTER);

                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Purchase {

            private String productName;
            private int quantity;
            private BigDecimal price;
            private BigDecimal total;

            public Purchase(String productName, int quantity, BigDecimal price) {
                this.productName = productName;
                this.quantity = quantity;
                this.price = price;
                this.total = price.multiply(BigDecimal.valueOf(quantity));
            }
        }
    }
}