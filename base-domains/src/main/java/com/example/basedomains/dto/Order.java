package com.example.basedomains.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {

    private String orderId;
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
