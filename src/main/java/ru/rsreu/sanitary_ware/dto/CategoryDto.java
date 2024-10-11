package ru.rsreu.sanitary_ware.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
}