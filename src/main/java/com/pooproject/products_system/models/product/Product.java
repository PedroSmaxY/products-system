package com.pooproject.products_system.models.product;

import com.pooproject.products_system.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = Product.TABLE_NAME)
public class Product {
    protected static final String TABLE_NAME = "products";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Product(ProductDTO productDTO) {
        this.name = productDTO.name();
        this.category = productDTO.category();
        this.price = productDTO.price();
        this.quantity = productDTO.quantity();
    }
}
