package ru.rsreu.sanitary_ware.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.sanitary_ware.repository.ProductRepository;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
}
