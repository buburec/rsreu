package ru.rsreu.sanitary_ware.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.sanitary_ware.dto.CartItemDto;
import ru.rsreu.sanitary_ware.entity.CartItem;
import ru.rsreu.sanitary_ware.mapper.CartItemMapper;
import ru.rsreu.sanitary_ware.repository.CartItemRepository;

@RequiredArgsConstructor
@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public void pushItem(Long id) {
        cartItemRepository.findById(id).ifPresent(cartItemRepository::save);
    }
}
