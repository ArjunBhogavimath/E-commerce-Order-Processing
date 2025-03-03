package com.ecommerce.order_processing.controller;

import com.ecommerce.order_processing.model.Order;
import com.ecommerce.order_processing.model.OrderStatusResponse;
import com.ecommerce.order_processing.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order/")
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public Order placeOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    //api to fetch the status of the order
    @GetMapping("{orderId}/status")
    public OrderStatusResponse getOrderStatus(@PathVariable Long orderId){
        return orderService.getStatus(orderId);
    }
}
