package com.backend.webclient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.backend.dto.Product;

@HttpExchange
public interface ProductClient {
    
    @GetExchange("/api/products/{id}")
    public Product findById(@PathVariable("id") Long id);
}
