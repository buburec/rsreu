package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.entity.Category;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category toEntity(ru.rsreu.sanitary_ware.dto.CategoryDto categoryDto);

    @AfterMapping
    default void linkProducts(@MappingTarget Category category) {
        category.getProducts().forEach(product -> product.setCategory(category));
    }

    ru.rsreu.sanitary_ware.dto.CategoryDto toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(ru.rsreu.sanitary_ware.dto.CategoryDto categoryDto, @MappingTarget Category category);
}