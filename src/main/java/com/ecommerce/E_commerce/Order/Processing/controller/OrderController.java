package com.ecommerce.E_commerce.Order.Processing.controller;

import com.ecommerce.E_commerce.Order.Processing.model.Order;
import com.ecommerce.E_commerce.Order.Processing.model.OrderStatusResponse;
import com.ecommerce.E_commerce.Order.Processing.service.OrderService;
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
