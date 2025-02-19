package com.akinade.catalog_hub.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest( // slice test annotation
        properties = {
            "spring.test.database.replace=none",
            "spring.datasource.url=jdbc:tc:mysql:8.0:///db",
        })
// @Import(ContainersConfig.class) // not advisable incase of other containers
// @Sql("/test-data.sql")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    // don't need to test the methods provided by Spring Data JPA.
    @Test
    void shouldGetAllProducts() {
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(4);
    }

    @Test
    void shouldGetProductByCode() {
        Product product = productRepository.findByCode("P100").orElseThrow();
        assertThat(product.getCode()).isEqualTo("P100");
        assertThat(product.getName()).isEqualTo("Casual T-Shirt");
        assertThat(product.getDescription()).isEqualTo("Comfortable cotton T-shirt");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal("25.00"));
    }

    @Test
    void shouldReturnEmptyWhenProductCodeNotExists() {
        assertThat(productRepository.findByCode("invalid_product_code")).isEmpty();
    }
}
