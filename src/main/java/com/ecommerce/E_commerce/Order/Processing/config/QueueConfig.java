package com.ecommerce.E_commerce.Order.Processing.config;

import com.ecommerce.E_commerce.Order.Processing.model.Order;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class QueueConfig {

    //the queue is created
    //with this the asynchronous handling of order we can achieve
    //everytime we can create new queue
    public BlockingQueue<Order> orderQueue(){
        return new LinkedBlockingQueue<>();
    }

}
