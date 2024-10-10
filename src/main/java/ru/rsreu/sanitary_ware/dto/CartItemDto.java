package ru.rsreu.sanitary_ware.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.CartItem}
 */
@Value
public class CartItemDto {
    Long id;
    @NotNull
    Integer quantity;
    @NotNull
    CartDto cart;
    @NotNull
    ProductDto product;
}