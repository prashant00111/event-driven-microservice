package com.stock.service.manager.orderservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String orderName;
    private int quantity;
    private double price;
}
