package com.akinade.catalog_hub.web.controllers;

import com.akinade.catalog_hub.domain.ProductDto;
import com.akinade.catalog_hub.domain.ProductNotFoundException;
import com.akinade.catalog_hub.domain.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return products.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }

    // Get a single product by its code
    @GetMapping("/{code}")
    public ResponseEntity<ProductDto> getProductByCode(@PathVariable String code) {
        log.info("Fetching product for code: {}", code);
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
