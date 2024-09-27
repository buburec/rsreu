package ru.rsreu.lab1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.rsreu.lab1.data.Item;
import ru.rsreu.lab1.data.Order;
import ru.rsreu.lab1.dto.ItemDTO;
import ru.rsreu.lab1.dto.OrderDTO;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
    ItemDTO toDTO(Item item);
    Item toEntity(ItemDTO itemDTO);
}
