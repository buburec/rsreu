package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.entity.Customer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CustomerOrderMapper.class, ReviewMapper.class})
public interface CustomerMapper {
    Customer toEntity(ru.rsreu.sanitary_ware.dto.CustomerDto customerDto);

    @AfterMapping
    default void linkCarts(@MappingTarget Customer customer) {
        customer.getCarts().forEach(cart -> cart.setCustomer(customer));
    }

    @AfterMapping
    default void linkCustomerOrders(@MappingTarget Customer customer) {
        customer.getCustomerOrders().forEach(customerOrder -> customerOrder.setCustomer(customer));
    }

    @AfterMapping
    default void linkReviews(@MappingTarget Customer customer) {
        customer.getReviews().forEach(review -> review.setCustomer(customer));
    }

    ru.rsreu.sanitary_ware.dto.CustomerDto toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer partialUpdate(ru.rsreu.sanitary_ware.dto.CustomerDto customerDto, @MappingTarget Customer customer);
}