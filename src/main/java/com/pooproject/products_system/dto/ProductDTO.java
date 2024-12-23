package com.pooproject.products_system.dto;

import com.pooproject.products_system.domain.category.Category;

import java.math.BigDecimal;

public record ProductDTO(String name, BigDecimal price, Integer quantity, Category category) {
}
