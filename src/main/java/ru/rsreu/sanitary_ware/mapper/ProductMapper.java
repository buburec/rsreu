package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.dto.ProductDto;
import ru.rsreu.sanitary_ware.entity.Category;
import ru.rsreu.sanitary_ware.entity.Product;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductDto productDto);

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "categoryId", target = "category")
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);

    default Category createCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }
}