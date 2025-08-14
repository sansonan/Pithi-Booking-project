package com.sonan.pithi_booking.controller;

import com.sonan.pithi_booking.dto.PageDTO;
import com.sonan.pithi_booking.dto.ProductCategoryDTO;
import com.sonan.pithi_booking.dto.ProductDTO;
import com.sonan.pithi_booking.entity.Product;
import com.sonan.pithi_booking.entity.ProductCategory;
import com.sonan.pithi_booking.service.ProductCategoryService;
import com.sonan.pithi_booking.service.util.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@Controller
@RequestMapping("/api/product-category") // Add this line
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService service;

    @Autowired
    private ProductCategoryMapper mapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @PostMapping("/addCategory")
    public ResponseEntity<ProductCategoryDTO> addProductCategory(@RequestBody ProductCategoryDTO dto) {
        System.out.println("Received DTO: " + dto.getCategoryName());
        ProductCategory productCategory = mapper.toEntity(dto);
        ProductCategory savedProductCategory = service.saveProductCategory(productCategory);
        return ResponseEntity.ok(mapper.toDto(savedProductCategory));
    }

//    @GetMapping
//    public ResponseEntity<List<ProductCategoryDTO>> getAllProductCategory() {
//        List<ProductCategory> categories = service.getAllProductCategory();
//        return ResponseEntity.ok(categories.stream().map(mapper::toDto).collect(Collectors.toList()));
//
//    }

    @GetMapping
    public ResponseEntity<Map<String, List<ProductCategoryDTO>>> getAllProductCategory() {
        List<ProductCategory> categories = service.getAllProductCategory();

        List<ProductCategoryDTO> dtoList = categories.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

        Map<String, List<ProductCategoryDTO>> response = new HashMap<>();
        response.put("list", dtoList);

        return ResponseEntity.ok(response);
    }





}
