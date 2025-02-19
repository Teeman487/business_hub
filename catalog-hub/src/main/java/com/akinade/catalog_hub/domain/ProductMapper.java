package com.akinade.catalog_hub.domain;

public class ProductMapper {
    // Entity to Dto
    static ProductDto mapToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setCode(product.getCode());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    // Dto to Entity
    public static Product mapToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setCode(productDto.getCode());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
