package com.example.demo.mapper;

import com.example.demo.config.MapperConfig;
import com.example.demo.dto.cartitem.CartItemDto;
import com.example.demo.dto.cartitem.CreateCartItemRequestDto;
import com.example.demo.model.CartItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(target = "shoppingCartId", ignore = true)
    CartItemDto toDto(CartItem book);

    @Mapping(target = "id", ignore = true)
    CartItem toModel(CreateCartItemRequestDto requestDto);

    @AfterMapping
    default void setShoppingCartId(@MappingTarget CartItemDto cartItemDto, CartItem cartItem) {
        cartItemDto.setShoppingCartId(cartItem.getShoppingCart().getId());
    }

    @AfterMapping
    default void setBookId(@MappingTarget CartItemDto cartItemDto, CartItem cartItem) {
        cartItemDto.setBookId(cartItem.getBook().getId());
    }

    @AfterMapping
    default void setBookTitle(@MappingTarget CartItemDto cartItemDto, CartItem cartItem) {
        cartItemDto.setBookTitle(cartItem.getBook().getTitle());
    }
}
