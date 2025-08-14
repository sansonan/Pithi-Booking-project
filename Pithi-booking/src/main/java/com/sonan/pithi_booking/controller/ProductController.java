package com.sonan.pithi_booking.controller;

import com.sonan.pithi_booking.dto.ApiResponseDTO;
import com.sonan.pithi_booking.dto.PageDTO;
import com.sonan.pithi_booking.dto.ProductDTO;
import com.sonan.pithi_booking.entity.Product;
import com.sonan.pithi_booking.service.ProductService;
import com.sonan.pithi_booking.service.util.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@Controller
@RequestMapping("/api/products") // Add this line
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;


    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productService.addProduct(product, productDTO.getCategoryId(), productDTO.getVendor().getId());
        return ResponseEntity.ok(productMapper.toDto(savedProduct));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable Long id, @RequestBody ProductDTO updatedProductDTO) {
        Product updateProduct = productService.updateProduct(id, productMapper.toEntity(updatedProductDTO));
        return ResponseEntity.ok(productMapper.toDto(updateProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/search/findByCategoryId")
//    public ResponseEntity<ProductDTO> findProductById(@RequestParam Long id) {
//        Product productId = productService.getProductaById(id);
//        return ResponseEntity.ok(productMapper.toDto(productId));
//    }

    @GetMapping("/search/findByCategoryId")
    public ResponseEntity<Map<String, Object>> findProductsByCategoryId(
            @RequestParam Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getProductsByCategoryId(id, pageable);
        Page<ProductDTO> productDTOPage = productPage.map(productMapper::toDto);

        Map<String, Object> response = new HashMap<>();
        response.put("list", productDTOPage.getContent());
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("pageNumber", productDTOPage.getNumber());
        pagination.put("pageSize", productDTOPage.getSize());
        pagination.put("totalPages", productDTOPage.getTotalPages());
        pagination.put("totalElements", productDTOPage.getTotalElements());
        pagination.put("numberOfElements", productDTOPage.getNumberOfElements());
        pagination.put("first", productDTOPage.isFirst());
        pagination.put("last", productDTOPage.isLast());
        pagination.put("empty", productDTOPage.isEmpty());

        response.put("pagination", pagination);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products.stream().map(productMapper::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestParam Map<String, String> filters) {
        Page<Product> page = productService.searchProduct(filters);
        Page<ProductDTO> dtoPage = page.map(productMapper::toDto);
        PageDTO<ProductDTO> response = new PageDTO<>(dtoPage);
        return ResponseEntity.ok(response);

    }

//    @GetMapping
//    public ResponseEntity<ApiResponseDTO<PageDTO<ProductDTO>>> filter(@RequestParam Map<String, String> filters) {
//        Page<Product> page = productService.searchProduct(filters);
//        Page<ProductDTO> dtoPage = page.map(productMapper::toDto);
//        PageDTO<ProductDTO> pageDTO = new PageDTO<>(dtoPage);
//
//        ApiResponseDTO<PageDTO<ProductDTO>> response = ApiResponseDTO.<PageDTO<ProductDTO>>builder()
//                .success(true)
//                .message("Product list fetched successfully")
//                .data(pageDTO)
//                .build();
//
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id) {
        Product productById = productService.getProductById(id);
        return ResponseEntity.ok(productMapper.toDto(productById));
    }


}
