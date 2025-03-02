package com.ecommerce.E_commerce.Order.Processing.repository;

import com.ecommerce.E_commerce.Order.Processing.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
