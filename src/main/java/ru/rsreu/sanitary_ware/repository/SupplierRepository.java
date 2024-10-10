package ru.rsreu.sanitary_ware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rsreu.sanitary_ware.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}