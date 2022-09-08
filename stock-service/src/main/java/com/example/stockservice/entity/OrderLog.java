package com.example.stockservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_log")
@Getter
@Setter
@ToString
public class OrderLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "status")
    private String status;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_quantity")
    private Integer orderQuantity;

    @Column(name = "order_price")
    private BigDecimal orderPrice;
}