package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.entity.Customer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    Customer toEntity(ru.rsreu.sanitary_ware.dto.CustomerDTO customerDTO);

    ru.rsreu.sanitary_ware.dto.CustomerDTO toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer partialUpdate(ru.rsreu.sanitary_ware.dto.CustomerDTO customerDTO, @MappingTarget Customer customer);
}