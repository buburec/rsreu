package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.dto.OrderItemDto;
import ru.rsreu.sanitary_ware.entity.CustomerOrder;
import ru.rsreu.sanitary_ware.entity.OrderItem;
import ru.rsreu.sanitary_ware.entity.Product;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "orderId", target = "order.id")
    OrderItem toEntity(OrderItemDto orderItemDto);

    @InheritInverseConfiguration(name = "toEntity")
    OrderItemDto toDto(OrderItem orderItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "orderId", target = "order")
    OrderItem partialUpdate(OrderItemDto orderItemDto, @MappingTarget OrderItem orderItem);

    default CustomerOrder createCustomerOrder(Long orderId) {
        if (orderId == null) {
            return null;
        }
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setId(orderId);
        return customerOrder;
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