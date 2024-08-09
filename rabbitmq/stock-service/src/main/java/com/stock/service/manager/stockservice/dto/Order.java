package com.stock.service.manager.stockservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String orderName;
    private int quantity;
    private double price;
}
