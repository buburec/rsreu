package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.entity.Review;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {
    Review toEntity(ru.rsreu.sanitary_ware.dto.ReviewDto reviewDto);

    ru.rsreu.sanitary_ware.dto.ReviewDto toDto(Review review);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Review partialUpdate(ru.rsreu.sanitary_ware.dto.ReviewDto reviewDto, @MappingTarget Review review);
}