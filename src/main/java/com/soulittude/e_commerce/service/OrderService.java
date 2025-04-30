package com.soulittude.e_commerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soulittude.e_commerce.dto.OrderResponseDTO;
import com.soulittude.e_commerce.entity.Cart;
import com.soulittude.e_commerce.entity.OrderEntity;
import com.soulittude.e_commerce.entity.OrderItem;
import com.soulittude.e_commerce.entity.OrderStatus;
import com.soulittude.e_commerce.entity.UserEntity;
import com.soulittude.e_commerce.repository.OrderRepository;
import com.soulittude.e_commerce.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;

    public OrderResponseDTO
    public OrderResponseDTO
    public OrderResponseDTO placeOrder(UserEntity user) {
        Cart cart = cartService.getCart(user);

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setOrder(order);
                    return orderItem;
                }).toList();

        order.setItems(orderItems);
        orderRepository.save(order);

        // Clear cart after order placement
        cart.getItems().clear();
        cart.calculateTotal();
        cartService.saveCart(cart);

        return order;
    }

    public List<OrderEntity> getUserOrders(UserEntity user) {
        return orderRepository.findByUser(user);
    }
}