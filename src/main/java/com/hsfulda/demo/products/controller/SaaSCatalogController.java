package com.hsfulda.demo.products.controller;

import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import com.hsfulda.demo.products.services.ExternalProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/saas")
public class SaaSCatalogController {

    private final ProductService productService;
    private final ExternalProductService externalProductService;

    public SaaSCatalogController(ProductService productService, ExternalProductService externalProductService) {
        this.productService = productService;
        this.externalProductService = externalProductService;
    }

    @Operation(summary = "Get all products")
    @GetMapping("/catalog")
    public List<Product> getCatalog(
            @org.springframework.web.bind.annotation.RequestHeader(value = "X-TENANT-ID", required = false) String tenantId) {
        List<Product> localProducts = productService.getProductsForTenant(tenantId);
        List<Product> externalProducts = externalProductService.fetchExternalProducts();

        List<Product> allProducts = new ArrayList<>(localProducts);
        allProducts.addAll(externalProducts);

        return allProducts;
    }
}
