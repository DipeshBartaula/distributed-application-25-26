package com.hsfulda.demo.controller;

import com.hsfulda.demo.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return Product.getProductList();
    }

    // Utility method to get product by id
    private Product getProductById(int id) {
        return Product.getProductList().stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    //Creating endpoint product/{id}
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id) {
        return getProductById(id);
    }

    //Get product by color
    @GetMapping("/product/color/{color}")
    public List<Product> getProductByColor(@PathVariable String color) {
        return Product.getProductList().stream()
                .filter(product -> product.getColor().equalsIgnoreCase(color))
                .toList();
    }

    //Get product by category and size
    @GetMapping("/product/category/{category}/size/{size}")
    public List<Product> getProductByCategoryAndSize(@PathVariable String category, @PathVariable String size) {
        return Product.getProductList().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category) && product.getSize().equalsIgnoreCase(size))
                .toList();
    }
}
