package com.ecommerce.order_processing.repository;

import com.ecommerce.order_processing.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT AVG(FUNCTION('EXTRACT', EPOCH FROM (o.updatedAt - o.createdAt))) FROM Order o WHERE o.status = 'COMPLETED'")
    Double findAverageProcessingTime();

    @Query("SELECT o.status, count(o) FROM order o GROUP BY o.status")
    List<Object[]> countByStatus();
}
