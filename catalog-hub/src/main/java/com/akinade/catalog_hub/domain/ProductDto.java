package com.akinade.catalog_hub.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDto {

    @NotEmpty(message = "Product code is required")
    private String code;

    @NotEmpty(message = "Product name is required")
    private String name;

    private String description;
    private String imageUrl;

    @NotNull(message = "Product price is required") @DecimalMin("0.1")
    private BigDecimal price;
}
