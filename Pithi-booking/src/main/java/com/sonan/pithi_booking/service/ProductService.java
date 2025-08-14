package com.sonan.pithi_booking.service;

import com.sonan.pithi_booking.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product addProduct(Product product, Long categoryId, Long vendorId);
    Product updateProduct(Long id, Product updateProduct);
    void deleteProduct(Long id);
    Page<Product> getProductsByCategoryId(Long categoryId, Pageable pageable);
    Page<Product> searchProduct(Map<String, String> params);

}
