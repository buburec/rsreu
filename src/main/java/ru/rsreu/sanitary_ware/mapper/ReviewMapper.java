package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.dto.ReviewDto;
import ru.rsreu.sanitary_ware.entity.Customer;
import ru.rsreu.sanitary_ware.entity.Product;
import ru.rsreu.sanitary_ware.entity.Review;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "customerId", target = "customer.id")
    Review toEntity(ReviewDto reviewDto);

    @InheritInverseConfiguration(name = "toEntity")
    ReviewDto toDto(Review review);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "customerId", target = "customer")
    Review partialUpdate(ReviewDto reviewDto, @MappingTarget Review review);

    default Customer createCustomer(Long customerId) {
        if (customerId == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerId);
        return customer;
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