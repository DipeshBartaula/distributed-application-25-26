package com.example.demo.products.services;

import com.example.demo.products.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> fetchAllProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(101L, "Fancy T-Shirt", new BigDecimal("19.99"), "M", "Red", "CLOTHING"));
        products.add(new Product(102L, "Fancy Socks", new BigDecimal("9.99"), "L", "Blue", "CLOTHING"));
        products.add(new Product(103L, "Running Shoes", new BigDecimal("89.50"), "42", "Black", "FOOTWEAR"));
        products.add(new Product(104L, "Baseball Cap", new BigDecimal("15.00"), "OneSize", "White", "ACCESSORIES"));
        products.add(new Product(105L, "Winter Jacket", new BigDecimal("120.00"), "L", "Grey", "CLOTHING"));

        return products;
    }
}
