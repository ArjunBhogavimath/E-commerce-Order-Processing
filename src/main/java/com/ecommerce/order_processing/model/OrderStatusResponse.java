package com.ecommerce.order_processing.model;

import lombok.Data;

@Data
public class OrderStatusResponse {
    private String status;

    public OrderStatusResponse(String status) {
        this.status = status;
    }
}
