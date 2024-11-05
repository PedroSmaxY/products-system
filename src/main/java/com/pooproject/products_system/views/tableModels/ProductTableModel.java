package com.pooproject.products_system.views.tableModels;

import com.pooproject.products_system.domain.product.Product;
import lombok.Setter;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {
    @Setter private List<Product> products;  // Lista de clientes
    private final String[] columnNames = {"ID", "Nome", "Preço", "Quantidade", "Categoria", "Editar", "Remover"};  // Nomes das colunas

    public ProductTableModel(List<Product> products) {
        this.products = products;
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = products.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> product.getId();
            case 1 -> product.getName();
            case 2 -> product.getPrice();
            case 3 -> product.getQuantity();
            case 4 -> product.getCategory().getName();
            case 5 -> "✏️";  // Ícone de editar (ou um texto temporário)
            case 6 -> "🗑️";  // Ícone de remover (ou um texto temporário)
            default -> null;
        };
    }

    public Product getProductAt(int rowIndex) {
        return products.get(rowIndex);
    }

    public void removeProduct(int rowIndex) {
        products.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

}
