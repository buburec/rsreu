package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.dto.CartDto;
import ru.rsreu.sanitary_ware.entity.Cart;
import ru.rsreu.sanitary_ware.entity.Customer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartMapper {
    @Mapping(source = "customerId", target = "customer.id")
    Cart toEntity(CartDto cartDto);

    @Mapping(source = "customer.id", target = "customerId")
    CartDto toDto(Cart cart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "customerId", target = "customer")
    Cart partialUpdate(CartDto cartDto, @MappingTarget Cart cart);

    default Customer createCustomer(Long customerId) {
        if (customerId == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerId);
        return customer;
    }
}