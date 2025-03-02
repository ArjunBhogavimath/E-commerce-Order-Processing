package com.ecommerce.E_commerce.Order.Processing.worker;

import com.ecommerce.E_commerce.Order.Processing.enums.OrderStatus;
import com.ecommerce.E_commerce.Order.Processing.model.Order;
import com.ecommerce.E_commerce.Order.Processing.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class OrderProcessingWorker {
    @Autowired
    private BlockingQueue<Order> orderQueue;

    @Autowired
    private OrderRepository orderRepository;

    @PostConstruct
    public void processOrders() {
        new Thread(() -> {
            while (true) {
                try {
                    // Take an order from the queue
                    Order order = orderQueue.take();

                    // Update the order status to PROCESSING
                    order.setStatus(OrderStatus.PROCESSING);
                    orderRepository.save(order);

                    // Simulate processing time (e.g., 5 seconds)
                    Thread.sleep(5000);

                    // Update the order status to COMPLETED
                    order.setStatus(OrderStatus.COMPLETED);
                    order.setUpdatedAt(java.time.LocalDateTime.now());
                    orderRepository.save(order);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }
}

