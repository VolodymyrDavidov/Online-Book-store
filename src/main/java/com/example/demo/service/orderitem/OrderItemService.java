package com.example.demo.service.orderitem;

import com.example.demo.dto.order.OrderItemDto;
import com.example.demo.model.OrderItem;

public interface OrderItemService {
    OrderItemDto findOrderItemByOrderIdAndItemId(Long orderId, Long itemId);

    OrderItem save(OrderItem orderItem);
}
