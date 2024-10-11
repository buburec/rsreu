package ru.rsreu.sanitary_ware.dto;

import lombok.Getter;
import lombok.Setter;
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
    Long cartId;
    Long productId;
}