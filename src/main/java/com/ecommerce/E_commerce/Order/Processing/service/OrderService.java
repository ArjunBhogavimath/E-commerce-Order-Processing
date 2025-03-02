package com.ecommerce.E_commerce.Order.Processing.service;

import com.ecommerce.E_commerce.Order.Processing.config.QueueConfig;
import com.ecommerce.E_commerce.Order.Processing.enums.OrderStatus;
import com.ecommerce.E_commerce.Order.Processing.model.Order;
import com.ecommerce.E_commerce.Order.Processing.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private QueueConfig queueConfig;

    public Order createOrder(Order order){
        //remember the order id will be created at run time
        //all the other fields we will get from user,
        //itemid,totalAmount and userId
        order.setStatus(OrderStatus.PENDING); //we should initialize it as pending 1st,as we are taking order
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        //the saved order will be put inside the queue for
        //asynchronous processing
        queueConfig.orderQueue().add(savedOrder);

        return savedOrder;
    }

    //api service to get the order status
    public String getStatus(Long orderId){
        // to get the status 1st i need to fetch the order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        //there will be cases where order will not be found,
        //so we either needs to cover it with optional, or throw the error if not found
        return order.getStatus().name();
    }
}
