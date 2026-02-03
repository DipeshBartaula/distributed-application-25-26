package com.example.demo.products.controller;

import com.example.demo.products.model.Product;
import com.example.demo.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/catalog")
    public List<Product> getAllProducts() {
        return productService.fetchAllProducts();
    }
}
