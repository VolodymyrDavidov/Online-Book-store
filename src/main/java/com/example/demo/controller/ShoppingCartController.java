package com.example.demo.controller;

import com.example.demo.dto.cartitem.CreateCartItemRequestDto;
import com.example.demo.dto.cartitem.UpdateQuantityInCartItemDto;
import com.example.demo.dto.shoppingcart.ShoppingCartDto;
import com.example.demo.service.shoppingcart.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ShoppingCart management", description = "Endpoints for managing shoppingCarts")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Retrieve user's shopping cart")
    @GetMapping
    public ShoppingCartDto getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }

    @Operation(summary = "Add book to the shopping cart")
    @PostMapping
    public ShoppingCartDto addNewBookToShoppingCart(
            CreateCartItemRequestDto createCartItemRequestDto) {
        return shoppingCartService.saveNewCartItem(createCartItemRequestDto);
    }

    @Operation(summary = "Update quantity of a book in the shopping cart")
    @PutMapping("/cart-items/{cartItemId}")
    public ShoppingCartDto updatingQuantityOfBook(@PathVariable Long id,
                                                 @Valid UpdateQuantityInCartItemDto
                                                         updateQuantityInCartItemDto) {
        return shoppingCartService.updateQuantity(id, updateQuantityInCartItemDto);
    }

    @Operation(summary = "Remove a book from the shopping cart")
    @DeleteMapping("/cart-items/{cartItemId}")
    public ShoppingCartDto deleteBookFromShoppingCart(@PathVariable Long id) {
        return shoppingCartService.deleteCartItem(id);
    }
}
