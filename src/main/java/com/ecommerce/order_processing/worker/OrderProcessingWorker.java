package com.ecommerce.order_processing.worker;

import com.ecommerce.order_processing.enums.OrderStatus;
import com.ecommerce.order_processing.model.Order;
import com.ecommerce.order_processing.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

//@Component
public class OrderProcessingWorker {
    @Autowired
    private BlockingQueue<Order> orderQueue;

    @Autowired
    private OrderRepository orderRepository;

  //  @PostConstruct
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

