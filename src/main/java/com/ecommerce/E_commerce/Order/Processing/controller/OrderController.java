package com.ecommerce.E_commerce.Order.Processing.controller;

import com.ecommerce.E_commerce.Order.Processing.model.Order;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order/")
public class OrderController {


    public Order placeOrder(@RequestBody Order order){
        return ;
    }
}
