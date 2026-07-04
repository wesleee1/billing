package com.example.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.billing.model.BillingOrder;
import com.example.billing.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public BillingOrder createOrder(BillingOrder order) {
        if (order.getQuantity() == null || order.getUnitPrice() == null) {
            throw new IllegalArgumentException("Quantity and unit price are required");
        }
        order.setTotalAmount(order.getQuantity() * order.getUnitPrice());
        order.setStatus("PAID");
        return orderRepository.save(order);
    }

    public List<BillingOrder> getAllOrders() {
        return orderRepository.findAll();
    }
}
