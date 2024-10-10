package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.rsreu.sanitary_ware.data.Order;
import ru.rsreu.sanitary_ware.dto.OrderDTO;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}
