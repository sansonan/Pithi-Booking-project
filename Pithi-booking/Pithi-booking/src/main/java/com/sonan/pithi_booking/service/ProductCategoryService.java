package com.sonan.pithi_booking.service;


import com.sonan.pithi_booking.entity.ProductCategory;


import java.util.List;


public interface ProductCategoryService {
    List<ProductCategory> getAllProductCategory();
    ProductCategory getProductCategoryById(Long id);
    ProductCategory saveProductCategory(ProductCategory productCategory);
    void updateProductCategory(Long id, ProductCategory productCategory);
    void deleteProductCategory(Long id);



}
