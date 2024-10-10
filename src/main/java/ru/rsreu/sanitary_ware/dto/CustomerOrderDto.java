package ru.rsreu.sanitary_ware.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.CustomerOrder}
 */
@Value
public class CustomerOrderDto {
    Long id;
    @NotNull
    Instant orderDate;
    @NotNull
    BigDecimal totalAmount;
    @Size(max = 255)
    String status;
}