package com.gabriel.ferreira.ms_customer.domain.repository;

import com.gabriel.ferreira.ms_customer.domain.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByCustomerId(Integer customerId);
}
