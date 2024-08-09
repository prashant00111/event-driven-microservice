package com.stock.service.manager.orderservice.controller;

import com.stock.service.manager.orderservice.dto.Order;
import com.stock.service.manager.orderservice.dto.OrderEvent;
import com.stock.service.manager.orderservice.publisher.OrderServiceProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class OrderRabbitMqController {

    private OrderServiceProducer serviceProducer;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("Pending");
        orderEvent.setMessage("Order is in pending status");
        orderEvent.setOrder(order);

        serviceProducer.sendMessage(orderEvent);
        return ResponseEntity.ok("Order has been placed successfully");
    }
}
