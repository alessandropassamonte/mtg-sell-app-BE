package com.mtgsell.mtgsellapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Date orderDate;

    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}