package com.base.domains.manager.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;
    private String orderName;
    private String type;
    private double price;
}
