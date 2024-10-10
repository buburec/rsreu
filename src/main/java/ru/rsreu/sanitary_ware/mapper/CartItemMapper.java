package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.dto.CartItemDto;
import ru.rsreu.sanitary_ware.entity.CartItem;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CartMapper.class, ProductMapper.class})
public interface CartItemMapper {
    CartItem toEntity(CartItemDto cartItemDto);

    CartItemDto toDto(CartItem cartItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CartItem partialUpdate(CartItemDto cartItemDto, @MappingTarget CartItem cartItem);

    CartItem updateWithNull(CartItemDto cartItemDto, @MappingTarget CartItem cartItem);
}