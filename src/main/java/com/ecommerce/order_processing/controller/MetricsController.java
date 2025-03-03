package com.ecommerce.order_processing.controller;

import com.ecommerce.order_processing.service.MetricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private MetricsService metricsService;

    //this call is not a order specific
    //this is to check the whole application
//    @GetMapping
//    public Map<String,Object> getMetrics(){
//       return Map.of(
//               "totalNumberOfOrderProcessed", metricsService.getTotalOrderProcessed(),
//               "AverageProcessingTimeForOrder", metricsService.getAvgProcessingTimeForOrder(),
//               "CountOfOrdersByStatus", metricsService.getCountOfOrdersByStatus()
//       );
//
//    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getMetrics() {
        return ResponseEntity.ok(metricsService.getMetrics());
    }
}
