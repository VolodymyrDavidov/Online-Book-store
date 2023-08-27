package com.example.demo.repository.orderitems;

import com.example.demo.model.OrderItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findOrderItem(Long orderId, Long itemId);
}
