package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.entity.Product;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CategoryMapper.class})
public interface ProductMapper {
    Product toEntity(ru.rsreu.sanitary_ware.dto.ProductDto productDto);

    @AfterMapping
    default void linkCartItems(@MappingTarget Product product) {
        product.getCartItems().forEach(cartItem -> cartItem.setProduct(product));
    }

    @AfterMapping
    default void linkOrderItems(@MappingTarget Product product) {
        product.getOrderItems().forEach(orderItem -> orderItem.setProduct(product));
    }

    @AfterMapping
    default void linkReviews(@MappingTarget Product product) {
        product.getReviews().forEach(review -> review.setProduct(product));
    }

    ru.rsreu.sanitary_ware.dto.ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ru.rsreu.sanitary_ware.dto.ProductDto productDto, @MappingTarget Product product);
}