package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.dto.CustomerOrderDto;
import ru.rsreu.sanitary_ware.entity.Customer;
import ru.rsreu.sanitary_ware.entity.CustomerOrder;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerOrderMapper {
    @Mapping(source = "customerId", target = "customer.id")
    CustomerOrder toEntity(CustomerOrderDto customerOrderDto);

    @Mapping(source = "customer.id", target = "customerId")
    CustomerOrderDto toDto(CustomerOrder customerOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "customerId", target = "customer")
    CustomerOrder partialUpdate(CustomerOrderDto customerOrderDto, @MappingTarget CustomerOrder customerOrder);

    default Customer createCustomer(Long customerId) {
        if (customerId == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerId);
        return customer;
    }
}