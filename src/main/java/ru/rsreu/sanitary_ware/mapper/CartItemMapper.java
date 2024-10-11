package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.dto.CartItemDto;
import ru.rsreu.sanitary_ware.entity.Cart;
import ru.rsreu.sanitary_ware.entity.CartItem;
import ru.rsreu.sanitary_ware.entity.Product;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartItemMapper {
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "cartId", target = "cart.id")
    CartItem toEntity(CartItemDto cartItemDto);

    @InheritInverseConfiguration(name = "toEntity")
    CartItemDto toDto(CartItem cartItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "cartId", target = "cart")
    CartItem partialUpdate(CartItemDto cartItemDto, @MappingTarget CartItem cartItem);

    default Cart createCart(Long cartId) {
        if (cartId == null) {
            return null;
        }
        Cart cart = new Cart();
        cart.setId(cartId);
        return cart;
    }

    default Product createProduct(Long productId) {
        if (productId == null) {
            return null;
        }
        Product product = new Product();
        product.setId(productId);
        return product;
    }
}