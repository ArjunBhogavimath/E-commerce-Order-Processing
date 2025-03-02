package com.ecommerce.E_commerce.Order.Processing.service;

import com.ecommerce.E_commerce.Order.Processing.enums.OrderStatus;
import com.ecommerce.E_commerce.Order.Processing.model.Order;
import com.ecommerce.E_commerce.Order.Processing.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public Order createOrder(Order order){
        //remember the order id will be created at run time
        //all the other fields we will get from user,
        //itemid,totalAmount and userId
        order.setStatus(OrderStatus.PENDING); //we should initialize it as pending 1st,as we are taking order
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        orderRepository.save(order);

    }
}
