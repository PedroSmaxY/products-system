package com.pooproject.products_system.views.tableModels;

import com.pooproject.products_system.domain.customer.Customer;
import lombok.Setter;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CustomerTableModel extends AbstractTableModel {
    @Setter private List<Customer> customers;  // Lista de clientes
    private final String[] columnNames = {"ID", "Nome", "Endereço", "Telefone", "E-mail", "Editar", "Remover"};  // Nomes das colunas

    public CustomerTableModel(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public int getRowCount() {
        return customers.size();
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
        Customer customer = customers.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> customer.getId();
            case 1 -> customer.getName();
            case 2 -> customer.getAddress();
            case 3 -> customer.getPhone();
            case 4 -> customer.getEmail();
            case 5 -> "✏️";  // Ícone de editar (ou um texto temporário)
            case 6 -> "🗑️";  // Ícone de remover (ou um texto temporário)
            default -> null;
        };
    }

    public Customer getCustomerAt(int rowIndex) {
        return customers.get(rowIndex);
    }

    public void removeCustomer(int rowIndex) {
        customers.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
