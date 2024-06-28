package com.gabriel.ferreira.ms_customer.domain.model.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabriel.ferreira.ms_customer.domain.enums.Sex;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name="customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String cpf;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdate;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private Boolean active;
}
