package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.dto.CartDTO;
import ru.rsreu.sanitary_ware.entity.Cart;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CustomerMapper.class})
public interface CartMapper {
    Cart toEntity(CartDTO cartDTO);

    @AfterMapping
    default void linkCartItems(@MappingTarget Cart cart) {
        cart.getCartItems().forEach(cartItem -> cartItem.setCart(cart));
    }

    CartDTO toDto(Cart cart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cart partialUpdate(CartDTO cartDTO, @MappingTarget Cart cart);
}