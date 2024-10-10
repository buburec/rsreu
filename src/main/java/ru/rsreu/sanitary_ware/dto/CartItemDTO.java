package ru.rsreu.sanitary_ware.dto;

import lombok.Value;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.CartItem}
 */
@Value
public class CartItemDTO {
    Long cartItemId;
    int quantity;
}