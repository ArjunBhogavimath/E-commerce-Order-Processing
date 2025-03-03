package com.ecommerce.order_processing.service;

import com.ecommerce.order_processing.config.QueueConfig;
import com.ecommerce.order_processing.enums.OrderStatus;
import com.ecommerce.order_processing.model.Order;
import com.ecommerce.order_processing.model.OrderStatusResponse;
import com.ecommerce.order_processing.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
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
    public OrderStatusResponse getStatus(Long orderId){
        // to get the status 1st i need to fetch the order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        //there will be cases where order will not be found,
        //so we either needs to cover it with optional, or throw the error if not found
        return new OrderStatusResponse(order.getStatus().name());
    }
}
