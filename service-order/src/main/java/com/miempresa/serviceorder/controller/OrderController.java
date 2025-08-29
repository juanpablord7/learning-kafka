package com.miempresa.serviceorder.controller;

import com.miempresa.serviceorder.dto.OrderRequest;
import com.miempresa.serviceorder.model.Order;
import com.miempresa.serviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    // Crear una nueva orden
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.ok(order);
    }
}
