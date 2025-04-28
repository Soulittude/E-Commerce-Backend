package com.soulittude.e_commerce.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soulittude.e_commerce.dto.CartItemDTO;
import com.soulittude.e_commerce.dto.CartResponseDTO;
import com.soulittude.e_commerce.dto.ProductDTO;
import com.soulittude.e_commerce.entity.Cart;
import com.soulittude.e_commerce.entity.CartItem;
import com.soulittude.e_commerce.entity.Product;
import com.soulittude.e_commerce.entity.UserEntity;
import com.soulittude.e_commerce.exception.ProductNotFoundException;
import com.soulittude.e_commerce.repository.CartItemRepository;
import com.soulittude.e_commerce.repository.CartRepository;
import com.soulittude.e_commerce.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public Cart getOrCreateCart(UserEntity user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setTotalPrice(BigDecimal.ZERO);
                    newCart.setItems(new ArrayList<>());
                    return cartRepository.save(newCart);
                });
    }

    public CartResponseDTO addToCart(Long productId, int quantity, UserEntity user) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Cart cart = getOrCreateCart(user);

        cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.setQuantity(item.getQuantity() + quantity),
                        () -> {
                            CartItem newItem = new CartItem();
                            newItem.setProduct(product);
                            newItem.setQuantity(quantity);
                            newItem.setCart(cart);
                            cartItemRepository.save(newItem);
                        });

        cart.calculateTotal();
        cartRepository.save(cart);

        return convertToDTO(cart);
    }

    public void removeFromCart(Long itemId, UserEntity user) {
        Cart cart = getOrCreateCart(user);
        cart.getItems().removeIf(item -> item.getId().equals(itemId));
        cart.calculateTotal();
        cartRepository.save(cart);
    }

    public Cart getCart(UserEntity user) {
        return getOrCreateCart(user);
    }

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public CartResponseDTO getCartDTO(UserEntity user) {
        Cart cart = getOrCreateCart(user);
        return convertToDTO(cart);
    }

    private CartResponseDTO convertToDTO(Cart cart) {
        CartResponseDTO dto = new CartResponseDTO();
        dto.setId(cart.getId());
        dto.setTotalPrice(cart.getTotalPrice());

        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(this::convertItemToDTO)
                .toList();

        dto.setItems(itemDTOs);
        return dto;
    }

    private CartItemDTO convertItemToDTO(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());

        Product product = item.getProduct();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());

        dto.setProduct(productDTO);
        return dto;
    }
}