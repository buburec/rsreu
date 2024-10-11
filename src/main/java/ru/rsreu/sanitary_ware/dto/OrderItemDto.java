package ru.rsreu.sanitary_ware.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.OrderItem}
 */
@Value
public class OrderItemDto {
    Long id;
    @NotNull
    Integer quantity;
    @NotNull
    BigDecimal price;
    Long orderId;
    Long productId;
}