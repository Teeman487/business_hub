package com.akinade.catalog_hub.domain;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll(Sort.by("name").ascending());
        return products.stream().map(ProductMapper::mapToProductDto).toList();
    }

    public Optional<ProductDto> getProductByCode(String code) {
        return productRepository.findByCode(code).map(ProductMapper::mapToProductDto);
    }
}
