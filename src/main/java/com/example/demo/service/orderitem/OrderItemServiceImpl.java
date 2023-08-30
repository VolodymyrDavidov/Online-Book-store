package com.example.demo.service.orderitem;

import com.example.demo.dto.order.OrderItemDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.mapper.OrderItemMapper;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.orderitems.OrderItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemMapper orderItemMapper;
    private final OrderItemsRepository orderItemsRepository;

    @Override
    public OrderItemDto findOrderItemByOrderIdAndItemId(Long orderId, Long itemId) {
        return orderItemMapper.toDto(orderItemsRepository
                .findOrderItemByOrderIdAndId(orderId, itemId).orElseThrow(() ->
                        new EntityNotFoundException("Can't find orderItem with order id: "
                                + orderId + " and item id: " + itemId)));
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemsRepository.save(orderItem);
    }
}
