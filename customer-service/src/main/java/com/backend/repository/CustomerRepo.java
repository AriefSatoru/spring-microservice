package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);
    
}
