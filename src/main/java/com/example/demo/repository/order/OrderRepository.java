package com.example.demo.repository.order;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getAllByUser(Pageable pageable, User user);
}
