package ru.rsreu.sanitary_ware.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

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
    Long categoryId;
}