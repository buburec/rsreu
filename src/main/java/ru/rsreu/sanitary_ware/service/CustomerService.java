package ru.rsreu.sanitary_ware.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.sanitary_ware.repository.CustomerRepository;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
}
