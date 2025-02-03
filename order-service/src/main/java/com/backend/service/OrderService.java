package com.backend.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.backend.dto.Customer;
import com.backend.dto.OrderDetailResponse;
import com.backend.dto.OrderResponse;
import com.backend.dto.Product;
import com.backend.entity.Order;
import com.backend.entity.OrderDetail;
import com.backend.repository.OrderRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
    
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private RestTemplate restTemplate;

    public Order save(Order order) {
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            orderDetail.setOrder(order);
        }

        return orderRepo.save(order);
    }

    public OrderResponse findById(Long id) {
        Optional<Order> optOrder = orderRepo.findById(id);
        if (!optOrder.isPresent()) {
            return null;
        }

        Order order = optOrder.get();
        OrderResponse orderResponse = new OrderResponse(order.getId(), order.getOrderNumber(),
            order.getOrderDate(), findCustomerById(order.getCustomerId()), new ArrayList<OrderDetailResponse>());

        for (OrderDetail orderDetail : order.getOrderDetails()) {
            Product product = findProductById(orderDetail.getProductId());
            orderResponse.getOrderDetailResponses().add(new OrderDetailResponse(orderDetail.getId(), product,
                orderDetail.getQuantity(), orderDetail.getPrice()));
        }

        return orderResponse;
    }

    public OrderResponse findByOrderNumber(String orderNumber) {
        Order order = orderRepo.findByOrderNumber(orderNumber);
        if (order == null) {
            return null;
        }

        OrderResponse orderResponse = new OrderResponse(order.getId(), order.getOrderNumber(),
            order.getOrderDate(), findCustomerById(order.getCustomerId()), new ArrayList<OrderDetailResponse>());

        for (OrderDetail orderDetail : order.getOrderDetails()) {
            Product product = findProductById(orderDetail.getProductId());
            orderResponse.getOrderDetailResponses().add(new OrderDetailResponse(orderDetail.getId(), product,
                orderDetail.getQuantity(), orderDetail.getPrice()));
        }

        return orderResponse;
    }

    private Customer findCustomerById(Long id) {
        return restTemplate.getForObject("http://CUSTOMER-SERVICE/api/customers/" + id, Customer.class);
    }

    private Product findProductById(Long id) {
        return restTemplate.getForObject("http://PRODUCT-SERVICE/api/products/" + id, Product.class);
    }
}
