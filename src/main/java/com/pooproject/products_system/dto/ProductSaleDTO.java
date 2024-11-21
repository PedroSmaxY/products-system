package com.pooproject.products_system.dto;

import com.pooproject.products_system.domain.product.Product;

public record ProductSaleDTO(Product product, Integer quantity) {
}
