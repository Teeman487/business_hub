package com.akinade.catalog_hub.web.controllers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

import com.akinade.catalog_hub.AbstractIT;
import com.akinade.catalog_hub.domain.ProductDto;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD) // execute b4 every test below
class ProductControllerTest extends AbstractIT {
    @Test
    void shouldReturnProducts() {
        List<ProductDto> products = given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".", ProductDto.class); // Extracting response as a list of ProductDto
        System.out.println("Total Products in DB: " + products.size()); // Debugging
        assertThat(products).isNotEmpty();
        assertThat(products).hasSize(4); // Assuming test data contains 4 products
    }

    @Test
    void shouldGetProductByCode() {
        ProductDto productDto = given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", "P100")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ProductDto.class);
        assertThat(productDto.getCode()).isEqualTo("P100");
        assertThat(productDto.getName()).isEqualTo("Casual T-Shirt");
        assertThat(productDto.getDescription()).isEqualTo("Comfortable cotton T-shirt");
        assertThat(productDto.getPrice()).isEqualByComparingTo(new BigDecimal("25.00"));
    }

    @Test
    void shouldReturnNotFoundWhenProductCodeNotExists() {
        String code = "invalid_product_code";
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product Not Found"))
                .body("detail", is("Product with code " + code + " not found"));
    }
}
