package com.pooproject.products_system.domain.costumer;

import com.pooproject.products_system.domain.sale.Sale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = Costumer.TABLE_NAME)
public class Costumer {
    protected static final String TABLE_NAME = "costumers";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    @OneToMany
    private List<Sale> sale;
}
