package com.example.demo.service.cartitem;

import com.example.demo.dto.cartitem.CreateCartItemRequestDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.mapper.CartItemMapper;
import com.example.demo.model.CartItem;
import com.example.demo.repository.cartitem.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartItem save(CreateCartItemRequestDto createCartItemRequestDto) {
        return cartItemRepository.save(cartItemMapper.toModel(createCartItemRequestDto));
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find cartItem by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't delete cartItem by id: " + id);
        }
        cartItemRepository.deleteById(id);
    }
}
