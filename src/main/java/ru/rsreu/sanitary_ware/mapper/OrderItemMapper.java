package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.dto.OrderItemDto;
import ru.rsreu.sanitary_ware.entity.OrderItem;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CustomerOrderMapper.class, ProductMapper.class})
public interface OrderItemMapper {
    OrderItem toEntity(OrderItemDto orderItemDto);

    OrderItemDto toDto(OrderItem orderItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderItem partialUpdate(OrderItemDto orderItemDto, @MappingTarget OrderItem orderItem);
}