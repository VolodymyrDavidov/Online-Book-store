package com.example.demo.service.order;

import com.example.demo.dto.order.MakeAnOrderDto;
import com.example.demo.dto.order.OrderDto;
import com.example.demo.dto.order.OrderItemDto;
import com.example.demo.dto.order.UpdateOrderStatusDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.mapper.OrderItemMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Book;
import com.example.demo.model.CartItem;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.ShoppingCart;
import com.example.demo.repository.book.BookRepository;
import com.example.demo.repository.order.OrderRepository;
import com.example.demo.service.orderitem.OrderItemService;
import com.example.demo.service.shoppingcart.ShoppingCartService;
import com.example.demo.service.user.UserService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemService orderItemService;
    private final BookRepository bookRepository;

    @Override
    public OrderDto makeOrder(MakeAnOrderDto makeAnOrderDto) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartModel();
        Order order = orderMapper.toOrderFromCart(shoppingCart);
        order.setShippingAddress(makeAnOrderDto.shippingAddress());
        Order savedOrder = orderRepository.save(order);
        Set<OrderItem> orderItems = getOrderItemsFromCart(shoppingCart);
        orderItems.forEach(orderItem -> orderItem.setOrder(savedOrder));
        savedOrder.setOrderItems(getSavedOrderItems(orderItems));
        shoppingCartService.confirmPurchase(shoppingCart);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public void updateOrderStatus(Long id, UpdateOrderStatusDto updateOrderStatusDto) {
        Order orderById = getOrderById(id);
        orderById.setOrderStatus(updateOrderStatusDto.orderStatus());
        orderRepository.save(orderById);
    }

    @Override
    public List<OrderItemDto> getOrderItems(Long id) {
        return getOrderById(id)
                .getOrderItems()
                .stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderDto> getOrderHistory(Pageable pageable) {
        return orderRepository.getAll(pageable, userService.getUser())
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemDto getOrderItem(Long orderId, Long itemId) {
        return orderItemService.findOrderItemByOrderIdAndItemId(orderId, itemId);
    }

    private Set<OrderItem> getOrderItemsFromCart(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems()
                .stream()
                .map(cartItem -> orderItemMapper.convertItem(cartItem,
                        getBookFromCartItem(cartItem)))
                .collect(Collectors.toSet());
    }

    private Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new EntityNotFoundException("Can't find order by id: " + orderId));
    }

    private Set<OrderItem> getSavedOrderItems(Set<OrderItem> orderItems) {
        return orderItems
                .stream()
                .map(orderItemService::save)
                .collect(Collectors.toSet());
    }

    private Book getBookFromCartItem(CartItem cartItem) {
        return bookRepository.findById(cartItem.getBook().getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find book by id: "
                        + cartItem.getBook().getId()));
    }
}
