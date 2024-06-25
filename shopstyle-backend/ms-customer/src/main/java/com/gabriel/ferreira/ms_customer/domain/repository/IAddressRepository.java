package com.gabriel.ferreira.ms_customer.domain.repository;

import com.gabriel.ferreira.ms_customer.domain.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Integer> {
}
