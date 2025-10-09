package com.ankitregmi.ecommerce.controller;

import com.ankitregmi.ecommerce.repository.OrderRepository;  // ✅ FIXED: use repository
import com.ankitregmi.ecommerce.entity.Order;
import com.ankitregmi.ecommerce.entity.OrderItem;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "https://aibazzar.netlify.app")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // ✅ Endpoint to get order items for a given order
    @GetMapping("/{orderId}/orderItems")
    public Set<OrderItem> getOrderItems(@PathVariable Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return order.getOrderItems();
    }
}
