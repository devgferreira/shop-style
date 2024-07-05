package com.gabriel.ferreira.ms_customer.domain.repository;

import com.gabriel.ferreira.ms_customer.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
   UserDetails findByEmail(String email);
}
