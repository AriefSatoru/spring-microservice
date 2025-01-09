package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Customer;
import com.backend.repository.CustomerRepo;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepo customerRepo;

    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    public Customer findById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }

    public Iterable<Customer> findAll() {
        return customerRepo.findAll();
    }

    public void deleteById(Long id) {
        customerRepo.deleteById(id);
    }

    public Customer findByEmail(String email) {
        return customerRepo.findByEmail(email);
    }
}
