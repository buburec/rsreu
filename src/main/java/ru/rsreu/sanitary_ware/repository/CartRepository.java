package ru.rsreu.sanitary_ware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rsreu.sanitary_ware.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}