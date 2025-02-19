package com.akinade.catalog_hub;

import org.springframework.boot.SpringApplication;

public class TestCatalogHubApplication {

    public static void main(String[] args) {
        SpringApplication.from(CatalogHubApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
