package com.order.manager.controller;

import com.base.domains.manager.dto.Order;
import com.base.domains.manager.dto.OrderEvent;
import com.order.manager.kafka.OrderProducer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class OrderController {

    private OrderProducer orderProducer;

    @PostMapping
    public String placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("Pending");
        orderEvent.setMessage("order status is pending state");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);

        return "Order Placed Successfully";

    }
}
