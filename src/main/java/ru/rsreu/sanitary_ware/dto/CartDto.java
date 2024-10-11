package ru.rsreu.sanitary_ware.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.Cart}
 */
@Value
public class CartDto {
    Long id;
    Long customerId;
}