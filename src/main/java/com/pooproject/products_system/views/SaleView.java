package com.pooproject.products_system.views;

import com.pooproject.products_system.domain.customer.Customer;
import com.pooproject.products_system.domain.product.Product;
import com.pooproject.products_system.dto.ProductSaleDTO;
import com.pooproject.products_system.services.CustomerService;
import com.pooproject.products_system.services.ProductService;
import com.pooproject.products_system.services.SaleService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

public class SaleView extends javax.swing.JFrame {

    private JFrame frame;
    private JTextField jTextClient;
    private JTextField jTextProduct;
    private JTextField jTextQuantity;
    private JTextField jTextPrice;
    private JTextField jTextTotal;
    private JTextField jTextDate;
    private JButton jButtonSelectClient;
    private JButton jButtonSelectProduct;
    private JButton jButtonAddProduct;
    private JButton jButtonSaveSale;
    private JButton jButtonCancelSale;

    private Customer activeCustomer;
    private List<ProductSaleDTO> activeProducts;
    private Product activeProduct;
    private SaleService saleService;
    private CustomerService customerService;
    private ProductService productService;

    private JTable tableSales;

    public SaleView() {
        saleService = new SaleService();
        customerService = new CustomerService();
        productService = new ProductService();
        activeProducts = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Cadastro de Vendas");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);

        JLabel jLabelTitle = new JLabel("Cadastro de Vendas", JLabel.CENTER);
        jLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel jLabelClient = new JLabel("Cliente:");
        JLabel jLabelProduct = new JLabel("Produto:");
        JLabel jLabelQuantity = new JLabel("Quantidade:");
        JLabel jLabelPrice = new JLabel("Preço Unitário:");
        JLabel jLabelTotal = new JLabel("Total:");
        JLabel jLabelDate = new JLabel("Data da Compra:");

        jTextClient = new JTextField();
        jTextClient.setEditable(false);

        jButtonSelectClient = new JButton("Selecionar Cliente");
        jButtonSelectClient.addActionListener(e -> abrirSelecaoCliente());

        jTextProduct = new JTextField();
        jTextProduct.setEditable(false);

        jButtonSelectProduct = new JButton("Selecionar Produto");
        jButtonSelectProduct.addActionListener(e -> abrirSelecaoProduto());

        jTextQuantity = new JTextField();

        jTextPrice = new JTextField();
        jTextPrice.setEditable(false);

        jButtonAddProduct = new JButton("Adicionar Produto");
        jButtonAddProduct.addActionListener(e -> adicionarProduto());

        jTextTotal = new JTextField();
        jTextTotal.setEditable(false);

        jTextDate = new JTextField();
        jTextDate.setEditable(false);
        jTextDate.setText(LocalDate.now().toString());

        jButtonSaveSale = new JButton("Salvar Venda");
        jButtonSaveSale.addActionListener(e -> salvarVenda());

        jButtonCancelSale = new JButton("Cancelar Venda"); // Novo botão
        jButtonCancelSale.setEnabled(false); // Inicialmente desativado
        jButtonCancelSale.addActionListener(e -> cancelarVenda());

        tableSales = new JTable(new DefaultTableModel(new Object[]{"Produto", "Quantidade", "Preço", "Total", "Deletar"}, 0)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

        tableSales.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel("🗑️", JLabel.CENTER);
                return label;
            }
        });

        tableSales.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableSales.rowAtPoint(e.getPoint());
                int column = tableSales.columnAtPoint(e.getPoint());

                if (column == 4) {
                    int confirm = JOptionPane.showConfirmDialog(frame, "Você tem certeza que deseja deletar este item?", "Confirmar Deleção", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        DefaultTableModel model = (DefaultTableModel) tableSales.getModel();
                        model.removeRow(row);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableSales);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelClient)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextClient, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonSelectClient))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelProduct)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextProduct, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonSelectProduct))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelQuantity)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextQuantity, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelPrice)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextPrice, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelTotal)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextTotal, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelDate)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextDate, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jButtonAddProduct)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButtonSaveSale)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonCancelSale)))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(30)
                        .addComponent(jLabelTitle)
                        .addGap(30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelClient)
                                .addComponent(jTextClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonSelectClient))
                        .addGap(18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelProduct)
                                .addComponent(jTextProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonSelectProduct))
                        .addGap(18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelQuantity)
                                .addComponent(jTextQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelPrice)
                                .addComponent(jTextPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelTotal)
                                .addComponent(jTextTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelDate)
                                .addComponent(jTextDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addComponent(jButtonAddProduct)
                        .addGap(18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonSaveSale)
                                .addComponent(jButtonCancelSale))
                        .addGap(20)
        );

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirSelecaoCliente() {
        JFrame clienteFrame = new JFrame("Selecionar Cliente");
        clienteFrame.setSize(600, 400);

        JPanel panelSearch = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField();
        panelSearch.add(new JLabel("Pesquisar Cliente:"), BorderLayout.WEST);
        panelSearch.add(searchField, BorderLayout.CENTER);

        JTable tableClientes = new JTable(new DefaultTableModel(new Object[]{"ID", "Nome"}, 0));
        List<Customer> clientes = customerService.findAll();

        DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
        for (Customer cliente : clientes) {
            model.addRow(new Object[]{cliente.getId(), cliente.getName()});
        }

        JScrollPane scrollPane = new JScrollPane(tableClientes);
        clienteFrame.add(panelSearch, BorderLayout.NORTH);
        clienteFrame.add(scrollPane, BorderLayout.CENTER);

        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText().toLowerCase();
                model.setRowCount(0);
                for (Customer cliente : clientes) {
                    if (cliente.getName().toLowerCase().contains(searchText)) {
                        model.addRow(new Object[]{cliente.getId(), cliente.getName()});
                    }
                }
            }
        });

        tableClientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableClientes.rowAtPoint(e.getPoint());
                Customer selectedCustomer = customerService.findById((Long) tableClientes.getValueAt(row, 0)).orElse(null);
                if (selectedCustomer != null) {
                    activeCustomer = selectedCustomer;
                    jTextClient.setText(selectedCustomer.getName());
                    jButtonSelectClient.setEnabled(false); // Desativa o botão
                    jButtonCancelSale.setEnabled(true); // Ativa "Cancelar Venda"
                    clienteFrame.dispose();
                }
            }
        });

        clienteFrame.setLocationRelativeTo(frame);
        clienteFrame.setVisible(true);
    }

    private void cancelarVenda() {
        jTextClient.setText("");
        jTextProduct.setText("");
        jTextQuantity.setText("");
        jTextPrice.setText("");
        jTextTotal.setText("");
        jButtonSelectClient.setEnabled(true);
        jButtonCancelSale.setEnabled(false);

        DefaultTableModel model = (DefaultTableModel) tableSales.getModel();
        model.setRowCount(0);
    }


        private void abrirSelecaoProduto() {
            JFrame produtoFrame = new JFrame("Selecionar Produto");
            produtoFrame.setSize(600, 400);

            JPanel panelSearch = new JPanel(new BorderLayout());
            JTextField searchField = new JTextField();
            panelSearch.add(new JLabel("Pesquisar Produto:"), BorderLayout.WEST);
            panelSearch.add(searchField, BorderLayout.CENTER);

            JTable tableProdutos = new JTable(new DefaultTableModel(new Object[]{"ID", "Nome", "Preço"}, 0));
            List<Product> produtos = productService.findAll();

            DefaultTableModel model = (DefaultTableModel) tableProdutos.getModel();
            for (Product produto : produtos) {
                model.addRow(new Object[]{produto.getId(), produto.getName(), produto.getPrice()});
            }

            JScrollPane scrollPane = new JScrollPane(tableProdutos);
            produtoFrame.add(panelSearch, BorderLayout.NORTH);
            produtoFrame.add(scrollPane, BorderLayout.CENTER);

            searchField.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    String searchText = searchField.getText().toLowerCase();
                    model.setRowCount(0); // Limpar a tabela antes de repopular
                    for (Product produto : produtos) {
                        if (produto.getName().toLowerCase().contains(searchText)) {
                            model.addRow(new Object[]{produto.getId(), produto.getName(), produto.getPrice()});
                        }
                    }
                }
            });

            tableProdutos.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int row = tableProdutos.rowAtPoint(e.getPoint());
                    Product selectedProduct = productService.findById((Long) tableProdutos.getValueAt(row, 0)).orElse(null);
                    if (selectedProduct != null) {
                        jTextProduct.setText(selectedProduct.getName());
                        jTextPrice.setText(selectedProduct.getPrice().toString());
                        activeProduct = selectedProduct;
                        produtoFrame.dispose();
                    }
                }
            });

            produtoFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    try {
                        produtoFrame.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Erro ao fechar a janela de produto: " + ex.getMessage());
                    }
                }
            });

            produtoFrame.setLocationRelativeTo(frame);
            produtoFrame.setVisible(true);
        }

        private void adicionarProduto() {
            String customerName = jTextClient.getText();
            String productName = jTextProduct.getText();
            String quantityText = jTextQuantity.getText();
            String priceText = jTextPrice.getText();
            if (activeProduct != null) {
                activeProducts.add(new ProductSaleDTO(activeProduct, Integer.valueOf(quantityText)));
            }

            if (customerName.isEmpty() || productName.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos!");
                return;
            }

            int quantity = Integer.parseInt(quantityText);
            BigDecimal price = new BigDecimal(priceText);

            BigDecimal total = price.multiply(new BigDecimal(quantity));
            jTextTotal.setText(total.toString());

            DefaultTableModel model = (DefaultTableModel) tableSales.getModel();
            model.addRow(new Object[]{productName, quantity, price, total});

            jTextProduct.setText("");
            jTextQuantity.setText("");
            jTextPrice.setText("");
        }

    private void salvarVenda() {
        try {
            var newSale = saleService.createSale(activeCustomer);

            activeProducts.forEach(productSaleDTO ->
                    saleService.addProductToSale(
                            newSale,
                            productSaleDTO.product(),
                            productSaleDTO.product().getPrice(),
                            productSaleDTO.quantity()
                    )
            );

            JOptionPane.showMessageDialog(this, "Venda salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            cancelarVenda();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar a venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new SaleView());
        }
    }