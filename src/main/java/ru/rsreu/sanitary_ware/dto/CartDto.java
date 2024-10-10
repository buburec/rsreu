package ru.rsreu.sanitary_ware.dto;

import lombok.Value;
import ru.rsreu.sanitary_ware.entity.CartItem;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.Cart}
 */
@Value
public class CartDto {
    Long id;
    @NotNull
    CustomerDto customer;
    Set<CartItemDto> cartItems;
}