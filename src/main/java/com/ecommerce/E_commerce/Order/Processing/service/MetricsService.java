package com.ecommerce.E_commerce.Order.Processing.service;

import com.ecommerce.E_commerce.Order.Processing.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MetricsService {

    private OrderRepository orderRepository;


    //we dont have these metrics details by default
    //so we need to write the native query manually
    //in jpa repository
    public Long getTotalOrderProcessed(){
        return orderRepository.count();
    }
    public Double getAvgProcessingTimeForOrder(){
        Double avg = orderRepository.findAverageProcessingTime();
        return avg != null ? avg : 0.0;
    }
    public Map<String, Object> getCountOfOrdersByStatus(){
        List<Object[]> statusCounts = orderRepository.countByStatus();
        return statusCounts.stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Long) obj[1]
                ));
    }


}
