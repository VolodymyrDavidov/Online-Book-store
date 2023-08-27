package com.example.demo.service.order;

import com.example.demo.dto.order.MakeAnOrderDto;
import com.example.demo.dto.order.OrderDto;
import com.example.demo.dto.order.OrderItemDto;
import com.example.demo.dto.order.UpdateOrderStatusDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDto makeOrder(MakeAnOrderDto makeAnOrderDto);

    void updateOrderStatus(Long id, UpdateOrderStatusDto updateOrderStatusDto);

    List<OrderItemDto> getOrderItems(Long id);

    List<OrderDto> getOrderHistory(Pageable pageable);

    OrderItemDto getOrderItem(Long orderId, Long itemId);
}
