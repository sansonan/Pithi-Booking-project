package com.sonan.pithi_booking.service.impl;

import com.sonan.pithi_booking.entity.Product;
import com.sonan.pithi_booking.entity.ProductCategory;
import com.sonan.pithi_booking.entity.Vendor;
import com.sonan.pithi_booking.exception.ResourceNotFoundException;
import com.sonan.pithi_booking.repository.ProductCategoryRepository;
import com.sonan.pithi_booking.repository.ProductRepository;
import com.sonan.pithi_booking.repository.VendorRepository;
import com.sonan.pithi_booking.service.ProductService;
import com.sonan.pithi_booking.service.util.PageUtil;
import com.sonan.pithi_booking.service.util.spec.ProductFilter;
import com.sonan.pithi_booking.service.util.spec.ProductSpec;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product addProduct(Product product, Long categoryId, Long vendorId) {
        ProductCategory category = productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        product.setCategory(category);
        product.setVendor(vendor);

        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(Long id, Product updateProduct) {
        Product exitingProduct = productRepository.findById(id)
                .orElseThrow(()
                        -> new ResourceNotFoundException("Product not found with id: " + id));
        exitingProduct.setId(updateProduct.getId());
        exitingProduct.setName(updateProduct.getName());
        exitingProduct.setDescription(updateProduct.getDescription());
        exitingProduct.setPrice(updateProduct.getPrice());
        exitingProduct.setCategory(updateProduct.getCategory());
        exitingProduct.setImageUrl(updateProduct.getImageUrl());
        exitingProduct.setLocation(updateProduct.getLocation());
        exitingProduct.setAvailability(updateProduct.getAvailability());
        return productRepository.save(exitingProduct);

    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);

    }

    @Override
    public Page<Product> getProductsByCategoryId(Long categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable);
    }



    @Override
    public Page<Product> searchProduct(Map<String, String> params) {
        ProductFilter filter = new ProductFilter();
        // Handle name filter
        String name = params.get("name");
        if (name != null && !name.trim().isEmpty()) {
            filter.setName(name.trim());
        }

        // Handle pagination
        int pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        int pageNumber = PageUtil.DEFAULT_PAGE_NUM;

        try {
            if (params.containsKey(PageUtil.PAGE_SIZE)) {
                pageSize = Integer.parseInt(params.get(PageUtil.PAGE_SIZE));
            }
        } catch (NumberFormatException ignored) {}

        try {
            if (params.containsKey(PageUtil.PAGE_NUM)) {
                pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUM));
            }
        } catch (NumberFormatException ignored) {}

        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize);
        ProductSpec productSpec = new ProductSpec(filter);

        return productRepository.findAll(productSpec, pageable);
    }

}
