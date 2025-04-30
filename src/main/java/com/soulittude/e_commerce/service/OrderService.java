package com.soulittude.e_commerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soulittude.e_commerce.dto.OrderItemDTO;
import com.soulittude.e_commerce.dto.OrderResponseDTO;
import com.soulittude.e_commerce.entity.Cart;
import com.soulittude.e_commerce.entity.CartItem;
import com.soulittude.e_commerce.entity.OrderEntity;
import com.soulittude.e_commerce.entity.OrderItem;
import com.soulittude.e_commerce.entity.OrderStatus;
import com.soulittude.e_commerce.entity.UserEntity;
import com.soulittude.e_commerce.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;

    public OrderResponseDTO placeOrder(UserEntity user) {
        Cart cart = cartService.getCart(user);
        OrderEntity order = new OrderEntity();

        // Entity mapping
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = cart.getItems().stream()
                .map(this::convertToOrderItem)
                .toList();

        order.setItems(orderItems);
        orderRepository.save(order);

        // Clear cart
        cart.getItems().clear();
        cart.calculateTotal();
        cartService.saveCart(cart);

        return convertToDTO(order);
    }

    public List<OrderResponseDTO> getUserOrders(UserEntity user) {
        return orderRepository.findByUser(user).stream()
                .map(this::convertToDTO)
                .toList();
    }

    private OrderItem convertToOrderItem(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        return orderItem;
    }

    private OrderResponseDTO convertToDTO(OrderEntity order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());

        dto.setItems(order.getItems().stream()
                .map(this::convertToItemDTO)
                .toList());

        return dto;
    }

    private OrderItemDTO convertToItemDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getProduct().getPrice());
        return dto;
    }
}