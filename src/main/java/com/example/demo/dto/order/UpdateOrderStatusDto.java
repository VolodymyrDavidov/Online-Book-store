package com.example.demo.dto.order;

import com.example.demo.model.Order;
import jakarta.validation.constraints.NotEmpty;

public record UpdateOrderStatusDto(@NotEmpty Order.OrderStatus orderStatus) {
}
