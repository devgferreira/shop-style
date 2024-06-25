package com.gabriel.ferreira.ms_customer.domain.model.address;

import com.gabriel.ferreira.ms_customer.domain.model.customer.Customer;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String state;
    private String city;
    private String street;
    private Integer number;
    private String cep;
    private String complement;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
