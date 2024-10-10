package ru.rsreu.sanitary_ware.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.sanitary_ware.repository.CustomerOrderRepository;

@RequiredArgsConstructor
@Service
public class CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;
}
