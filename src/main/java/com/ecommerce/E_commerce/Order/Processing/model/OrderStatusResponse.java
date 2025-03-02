package com.ecommerce.E_commerce.Order.Processing.model;

import lombok.Data;

@Data
public class OrderStatusResponse {
    private String status;

    public OrderStatusResponse(String status) {
        this.status = status;
    }
}
