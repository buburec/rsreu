package ru.rsreu.lab1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.rsreu.lab1.data.Order;
import ru.rsreu.lab1.dto.OrderDTO;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}
