package com.ecommerce.order_processing.repository;

import com.ecommerce.order_processing.enums.OrderStatus;
import com.ecommerce.order_processing.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    long countByStatus(OrderStatus status);

    List<Order> findByStatus(OrderStatus status);
}
