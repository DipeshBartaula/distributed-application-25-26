package com.hsfulda.demo.products.controller;

import com.hsfulda.demo.products.facade.ProductCatalogFacade;
import com.hsfulda.demo.products.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/saas")
public class SaaSCatalogController {

    private final ProductCatalogFacade productCatalogFacade;

    public SaaSCatalogController(ProductCatalogFacade productCatalogFacade) {
        this.productCatalogFacade = productCatalogFacade;
    }

    @Operation(summary = "Get all products")
    @GetMapping("/catalog")
    public List<Product> getCatalog() {
        return productCatalogFacade.getProductList().product;
    }
}
