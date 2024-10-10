package ru.rsreu.sanitary_ware.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.Category}
 */
@Value
public class CategoryDto {
    Long id;
    @NotNull
    @Size(max = 255)
    String name;
    @Size(max = 255)
    String description;
    Set<ProductDto> products;
}