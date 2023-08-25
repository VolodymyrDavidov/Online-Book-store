package com.example.demo.mapper;

import com.example.demo.config.MapperConfig;
import com.example.demo.dto.order.OrderItemDto;
import com.example.demo.model.Book;
import com.example.demo.model.CartItem;
import com.example.demo.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {

    OrderItemDto toDto(OrderItem orderItem);

    @Mapping(source = "cartItem.id", target = "id")
    @Mapping(source = "book.deleted", target = "deleted")
    OrderItem convertItem(CartItem cartItem, Book book);
}
