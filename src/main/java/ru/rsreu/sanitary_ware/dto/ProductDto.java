package ru.rsreu.sanitary_ware.dto;

import lombok.Value;
import ru.rsreu.sanitary_ware.entity.CartItem;
import ru.rsreu.sanitary_ware.entity.OrderItem;
import ru.rsreu.sanitary_ware.entity.Review;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.Product}
 */
@Value
public class ProductDto {
    Long id;
    @NotNull
    @Size(max = 255)
    String name;
    @Size(max = 255)
    String description;
    @NotNull
    BigDecimal price;
    @NotNull
    Integer stockQuantity;
    @Size(max = 255)
    String imageUrl;
    @NotNull
    CategoryDto category;
    Set<CartItemDto> cartItems;
    Set<OrderItemDto> orderItems;
    Set<ReviewDto> reviews;
}