package ru.rsreu.sanitary_ware.dto;

import lombok.Value;
import ru.rsreu.sanitary_ware.entity.Cart;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.Customer}
 */
@Value
public class CustomerDto {
    Long id;
    @NotNull
    @Size(max = 255)
    String name;
    @NotNull
    @Size(max = 255)
    String email;
    @Size(max = 20)
    String phone;
    @Size(max = 255)
    String address;
    Set<CartDto> carts;
    Set<CustomerOrderDto> customerOrders;
    Set<ReviewDto> reviews;
}