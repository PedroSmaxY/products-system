package com.pooproject.products_system.domain.customer;

import com.pooproject.products_system.domain.sale.Sale;
import com.pooproject.products_system.dto.CustomerDTO;
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
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private String address;

    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sales;

    public Customer(CustomerDTO customerDTO) {
        this.name = customerDTO.name();
        this.address = customerDTO.address();
        this.phone = customerDTO.phone();
        this.email = customerDTO.email();
    }
}
