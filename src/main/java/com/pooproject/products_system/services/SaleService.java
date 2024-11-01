package com.pooproject.products_system.services;

import com.pooproject.products_system.dao.ProductSaleDAO;
import com.pooproject.products_system.dao.SaleDAO;
import com.pooproject.products_system.domain.product.Product;
import com.pooproject.products_system.domain.productSale.ProductSale;
import com.pooproject.products_system.domain.sale.Sale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class SaleService {
    private final SaleDAO saleDAO = new SaleDAO();
    private final ProductSaleDAO productSaleDAO = new ProductSaleDAO();
    private final ProductSaleService productSaleService = new ProductSaleService(productSaleDAO);

    public Sale createSale() {
        Sale sale = new Sale();
        sale.setCurrentDate(LocalDate.now());
        sale.setTotalPrice(BigDecimal.ZERO);
        saleDAO.save(sale);
        return sale;
    }

    public void addProductToSale(Sale sale, Product product, BigDecimal price, Integer quantity) {
        ProductSale productSale = productSaleService.createProductSale(sale, product, price, quantity);

        BigDecimal total = sale.getTotalPrice().add(price.multiply(new BigDecimal(quantity)));
        sale.setTotalPrice(total);
        saleDAO.update(sale);
    }

    public Sale findSaleById(Long id) {
        return saleDAO.findById(id);
    }

    public List<Sale> findSalesByDate(LocalDate date) {
        return saleDAO.findByDate(date);
    }

    public List<Sale> findAllSales() {
        return saleDAO.findAll();
    }

    public void deleteSale(Long id) {
        saleDAO.delete(id);
    }
}
