package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.rsreu.sanitary_ware.data.Item;
import ru.rsreu.sanitary_ware.dto.ItemDTO;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
    ItemDTO toDTO(Item item);
    Item toEntity(ItemDTO itemDTO);
}
