package ru.rsreu.sanitary_ware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rsreu.sanitary_ware.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}