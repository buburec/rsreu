package ru.rsreu.sanitary_ware.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.sanitary_ware.dto.CartDto;
import ru.rsreu.sanitary_ware.dto.ProductDto;
import ru.rsreu.sanitary_ware.entity.Cart;
import ru.rsreu.sanitary_ware.entity.Product;
import ru.rsreu.sanitary_ware.mapper.CartMapper;
import ru.rsreu.sanitary_ware.repository.CartRepository;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartDto initCart(Long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            cart = new Cart();
            cart.setId(id);
        }
        return cartMapper.toDto(cart);
    }
}
