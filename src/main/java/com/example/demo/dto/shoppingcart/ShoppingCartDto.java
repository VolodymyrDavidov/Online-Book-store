package com.example.demo.dto.shoppingcart;

import com.example.demo.dto.cartitem.CartItemDto;
import java.util.Set;

public class ShoppingCartDto {
    private Long id;
    private Long userId;
    private Set<CartItemDto> cartItemDtoSet;
}
