package com.example.demo.dto.cartitem;

import jakarta.validation.constraints.PositiveOrZero;

public record UpdateQuantityInCartItemDto(@PositiveOrZero int quantity) {
}
