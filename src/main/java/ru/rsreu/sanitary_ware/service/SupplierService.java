package ru.rsreu.sanitary_ware.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.sanitary_ware.repository.SupplierRepository;

@RequiredArgsConstructor
@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
}
