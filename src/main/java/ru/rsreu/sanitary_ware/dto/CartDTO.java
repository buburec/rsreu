package ru.rsreu.sanitary_ware.dto;

import lombok.Value;

import java.util.List;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.Cart}
 */
@Value
public class CartDTO {
    Long cartId;
    CustomerDTO customer;
    List<CartItemDTO> cartItems;
}