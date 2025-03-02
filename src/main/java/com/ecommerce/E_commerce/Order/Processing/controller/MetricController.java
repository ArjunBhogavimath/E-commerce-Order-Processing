package com.ecommerce.E_commerce.Order.Processing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/metrics")
public class MetricController {

    //this call is not a order specific
    //this is to check the whole application
    @GetMapping
    public Map<String,Object> getMetrics(){
       return Map.of(
               "totalNumberOfOrderProcessed", metrM
       )
    }

    /**
     * Total number of orders processed.
     *
     * Average processing time for orders.
     *
     * Count of orders in each status -
     *
     * Pending, 
     *
     * Processing, 
     *
     * Completed
     */
}
