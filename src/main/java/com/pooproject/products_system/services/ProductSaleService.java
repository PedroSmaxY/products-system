package com.pooproject.products_system.services;

import com.pooproject.products_system.dao.ProductSaleDAO;
import com.pooproject.products_system.domain.product.Product;
import com.pooproject.products_system.domain.productSale.ProductSale;
import com.pooproject.products_system.domain.sale.Sale;

import java.math.BigDecimal;

public class ProductSaleService {
    private final ProductSaleDAO productSaleDAO;

    public ProductSaleService(ProductSaleDAO productSaleDAO) {
        this.productSaleDAO = productSaleDAO;
    }

    public ProductSale createProductSale(Sale sale, Product product, BigDecimal price, Integer quantity) {
        ProductSale productSale = new ProductSale();
        productSale.setProduct(product);
        productSale.setSale(sale);
        productSale.setPrice(price);
        productSale.setQuantity(quantity);
        productSaleDAO.save(productSale);
        return productSale;
    }
}
