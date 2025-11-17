package com.hsfulda.demo.products.model;

import jakarta.persistence.Entity;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    public Map<Product, Integer> products = new HashMap<>();

    public Double getTotalPrice() {
        return products.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }
}
