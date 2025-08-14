package com.sonan.pithi_booking.service.util;

import com.sonan.pithi_booking.dto.ProductDTO;
import com.sonan.pithi_booking.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { VendorMapper.class, ProductCategoryMapper.class })
public interface ProductMapper {


    @Mapping(source = "category.id", target = "categoryId")
    ProductDTO toDto (Product product);

    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDTO productDTO);
}
