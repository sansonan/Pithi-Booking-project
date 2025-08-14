package com.sonan.pithi_booking.service.util;

import com.sonan.pithi_booking.dto.ProductCategoryDTO;
import com.sonan.pithi_booking.entity.ProductCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    ProductCategoryDTO toDto(ProductCategory productCategory);
    ProductCategory toEntity(ProductCategoryDTO productCategoryDTO);

}