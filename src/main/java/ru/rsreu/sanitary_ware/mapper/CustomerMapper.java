package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.dto.CustomerDto;
import ru.rsreu.sanitary_ware.entity.Customer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    Customer toEntity(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer partialUpdate(CustomerDto customerDto, @MappingTarget Customer customer);
}