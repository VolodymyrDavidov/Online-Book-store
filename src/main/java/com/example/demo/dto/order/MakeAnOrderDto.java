package com.example.demo.dto.order;

import jakarta.validation.constraints.NotEmpty;

public record MakeAnOrderDto(@NotEmpty String shippingAddress) {
}
