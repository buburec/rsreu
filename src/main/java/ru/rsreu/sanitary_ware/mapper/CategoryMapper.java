package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.dto.CategoryDto;
import ru.rsreu.sanitary_ware.entity.Category;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryDto categoryDto, @MappingTarget Category category);
}