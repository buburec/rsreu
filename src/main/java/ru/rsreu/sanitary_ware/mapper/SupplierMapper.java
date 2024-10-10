package ru.rsreu.sanitary_ware.mapper;

import org.mapstruct.*;
import ru.rsreu.sanitary_ware.dto.SupplierDto;
import ru.rsreu.sanitary_ware.entity.Supplier;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupplierMapper {
    Supplier toEntity(SupplierDto supplierDto);

    SupplierDto toDto(Supplier supplier);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Supplier partialUpdate(SupplierDto supplierDto, @MappingTarget Supplier supplier);
}