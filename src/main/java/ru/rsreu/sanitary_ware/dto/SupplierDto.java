package ru.rsreu.sanitary_ware.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.Supplier}
 */
@Value
public class SupplierDto {
    Long id;
    @NotNull
    @Size(max = 255)
    String name;
    @Size(max = 255)
    String contactPerson;
    @Size(max = 20)
    String contactPhone;
    @Size(max = 255)
    String email;
}