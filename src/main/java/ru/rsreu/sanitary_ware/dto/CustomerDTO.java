package ru.rsreu.sanitary_ware.dto;

import lombok.Value;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.Customer}
 */
@Value
public class CustomerDTO {
    Long customerId;
    String name;
    String email;
    String phone;
    String address;
}