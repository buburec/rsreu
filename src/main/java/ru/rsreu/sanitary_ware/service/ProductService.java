package ru.rsreu.sanitary_ware.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.sanitary_ware.dto.ProductDto;
import ru.rsreu.sanitary_ware.entity.Product;
import ru.rsreu.sanitary_ware.mapper.ProductMapper;
import ru.rsreu.sanitary_ware.repository.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toDto).toList();
    }
}
