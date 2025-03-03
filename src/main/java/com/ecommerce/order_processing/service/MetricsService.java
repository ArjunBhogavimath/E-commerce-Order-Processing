package com.ecommerce.order_processing.service;

import com.ecommerce.order_processing.enums.OrderStatus;
import com.ecommerce.order_processing.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MetricsService {

    private OrderRepository orderRepository;

    public Map<String, Object> getMetrics() {
        long pending = orderRepository.countByStatus(OrderStatus.PENDING);
        long processing = orderRepository.countByStatus(OrderStatus.PROCESSING);
        long completed = orderRepository.countByStatus(OrderStatus.COMPLETED);

        double avgProcessingTime = orderRepository.findByStatus(OrderStatus.COMPLETED)
                .stream()
                .mapToDouble(order -> Duration.between(order.getCreatedAt(), order.getUpdatedAt()).toMillis())
                .average()
                .orElse(0);

        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalOrders", pending + processing + completed);
        metrics.put("averageProcessingTimeMs", avgProcessingTime);
        metrics.put("pendingOrders", pending);
        metrics.put("processingOrders", processing);
        metrics.put("completedOrders", completed);

        return metrics;
    }


    //we dont have these metrics details by default
    //so we need to write the native query manually
//    //in jpa repository
//    public Long getTotalOrderProcessed(){
//        return orderRepository.count();
//    }
//    public Double getAvgProcessingTimeForOrder(){
//        Double avg = orderRepository.findAverageProcessingTime();
//        return avg != null ? avg : 0.0;
//    }
//    public Map<String, Object> getCountOfOrdersByStatus(){
//        List<Object[]> statusCounts = orderRepository.countByStatus();
//        return statusCounts.stream()
//                .collect(Collectors.toMap(
//                        obj -> (String) obj[0],
//                        obj -> (Long) obj[1]
//                ));
//    }


}
