package com.pooproject.products_system.domain.sale;

import com.pooproject.products_system.domain.costumer.Costumer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = Sale.TABLE_NAME)
public class Sale {
    protected static final String TABLE_NAME = "sales";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate currentDate = LocalDate.now();

    private BigDecimal totalPrice;

    @ManyToOne
    private Costumer costumer;
}
