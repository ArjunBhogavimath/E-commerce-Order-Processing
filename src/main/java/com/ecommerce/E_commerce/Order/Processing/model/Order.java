package com.ecommerce.E_commerce.Order.Processing.model;

import com.ecommerce.E_commerce.Order.Processing.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
//@Table(name = "order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long userID;

    @Column(columnDefinition = "jsonb") //it will make list of strings to json binary object
    public String itemIds;

    public double totalAmount;

    @Enumerated(EnumType.STRING) //convert enums to string
    public OrderStatus status;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;
}
