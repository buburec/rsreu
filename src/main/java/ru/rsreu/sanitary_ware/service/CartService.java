package ru.rsreu.sanitary_ware.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.sanitary_ware.repository.CartRepository;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
}
