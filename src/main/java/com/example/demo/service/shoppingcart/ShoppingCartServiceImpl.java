package com.example.demo.service.shoppingcart;

import com.example.demo.dto.cartitem.CreateCartItemRequestDto;
import com.example.demo.dto.cartitem.UpdateQuantityInCartItemDto;
import com.example.demo.dto.shoppingcart.ShoppingCartDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.mapper.ShoppingCartMapper;
import com.example.demo.model.CartItem;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.repository.shoppingcart.ShoppingCartRepository;
import com.example.demo.service.cartitem.CartItemService;
import com.example.demo.service.user.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartMapper shoppingCartMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemService cartItemService;
    private final UserService userService;

    @Override
    public ShoppingCartDto getShoppingCart() {
        return shoppingCartMapper.toDto(getUserShoppingCart());
    }

    @Override
    public ShoppingCartDto saveNewCartItem(CreateCartItemRequestDto createCartItemRequestDto) {
        ShoppingCart shoppingCart = getUserShoppingCart();
        CartItem cartItem = cartItemService.save(createCartItemRequestDto);
        shoppingCart.setCartItems((Set<CartItem>) cartItem);
        return getShoppingCart();
    }

    @Override
    public ShoppingCartDto deleteCartItem(Long id) {
        cartItemService.deleteById(id);
        return getShoppingCart();
    }

    @Override
    public ShoppingCartDto updateQuantity(Long id,
                                          UpdateQuantityInCartItemDto updateQuantityInCartItemDto) {
        cartItemService.findById(id).setQuantity(updateQuantityInCartItemDto.quantity());
        return getShoppingCart();
    }

    private ShoppingCart getUserShoppingCart() {
        User user = userService.getUser();
        return shoppingCartRepository.findById(userService.getUser().getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find cart by user id: " + user.getId()));
    }
}
