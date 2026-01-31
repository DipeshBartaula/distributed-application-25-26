package com.hsfulda.demo.products.controller;

import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/saas")
public class SaaSCatalogController {

    private final ProductService productService;

    public SaaSCatalogController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products")
    @GetMapping("/catalog")
    public List<Product> getCatalog(
            @org.springframework.web.bind.annotation.RequestHeader(value = "X-TENANT-ID", required = false) String tenantId) {
        return productService.getProductsForTenant(tenantId);
    }
}
