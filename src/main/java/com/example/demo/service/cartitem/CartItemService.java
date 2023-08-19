package com.example.demo.service.cartitem;

import com.example.demo.dto.cartitem.CreateCartItemRequestDto;
import com.example.demo.model.CartItem;

public interface CartItemService {
    CartItem save(CreateCartItemRequestDto createCartItemRequestDto);

    CartItem findById(Long id);

    void deleteById(Long id);
}
