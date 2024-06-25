package com.gabriel.ferreira.ms_customer.domain.repository;

import com.gabriel.ferreira.ms_customer.domain.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
}
