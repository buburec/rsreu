package ru.rsreu.sanitary_ware.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.sanitary_ware.repository.OrderItemRepository;

@RequiredArgsConstructor
@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
}
