package com.sonan.pithi_booking.service.impl;

import com.sonan.pithi_booking.entity.Product;
import com.sonan.pithi_booking.entity.ProductCategory;
import com.sonan.pithi_booking.exception.ResourceNotFoundException;
import com.sonan.pithi_booking.repository.ProductCategoryRepository;
import com.sonan.pithi_booking.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public List<ProductCategory> getAllProductCategory() {
        return repository.findAll();
    }

    @Override
    public ProductCategory getProductCategoryById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        ProductCategory savedCategory = new ProductCategory();
        savedCategory.setCategoryName(productCategory.getCategoryName());
        return repository.save(savedCategory);
    }

    @Override
    public void updateProductCategory(Long id, ProductCategory updatedProductCategory) {
        ProductCategory exitingCategory= getProductCategoryById(id);
        exitingCategory.setId(updatedProductCategory.getId());
        exitingCategory.setCategoryName(updatedProductCategory.getCategoryName());
        repository.save(exitingCategory);

    }

    @Override
    public void deleteProductCategory(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        repository.deleteById(id);
    }




}
