package com.example.demo.service.shoppingcart;

import com.example.demo.dto.cartitem.CreateCartItemRequestDto;
import com.example.demo.dto.cartitem.UpdateQuantityInCartItemDto;
import com.example.demo.dto.shoppingcart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto getShoppingCart();

    ShoppingCartDto saveNewCartItem(CreateCartItemRequestDto createCartItemRequestDto);

    ShoppingCartDto deleteCartItem(Long id);

    ShoppingCartDto updateQuantity(Long id,
                                   UpdateQuantityInCartItemDto updateQuantityInCartItemDto);
}
