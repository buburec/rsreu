package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.entity.CustomerOrder;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerOrderMapper {
    CustomerOrder toEntity(ru.rsreu.sanitary_ware.dto.CustomerOrderDto customerOrderDto);

    ru.rsreu.sanitary_ware.dto.CustomerOrderDto toDto(CustomerOrder customerOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CustomerOrder partialUpdate(ru.rsreu.sanitary_ware.dto.CustomerOrderDto customerOrderDto, @MappingTarget CustomerOrder customerOrder);
}