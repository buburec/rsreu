package ru.rsreu.sanitary_ware.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.sanitary_ware.repository.CartItemRepository;

@RequiredArgsConstructor
@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
}
