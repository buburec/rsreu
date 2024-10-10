package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.entity.Cart;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CustomerMapper.class})
public interface CartMapper {
    Cart toEntity(ru.rsreu.sanitary_ware.dto.CartDto cartDto);

    @AfterMapping
    default void linkCartItems(@MappingTarget Cart cart) {
        cart.getCartItems().forEach(cartItem -> cartItem.setCart(cart));
    }

    ru.rsreu.sanitary_ware.dto.CartDto toDto(Cart cart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cart partialUpdate(ru.rsreu.sanitary_ware.dto.CartDto cartDto, @MappingTarget Cart cart);
}