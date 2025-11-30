package com.hsfulda.demo.products.dto;

import com.hsfulda.demo.products.model.Product;

import java.util.List;

public class ProductCatalogDTO {
    public List<Product> product;
    public int stock;

    public ProductCatalogDTO(List<Product> product, int stock) {
        this.product = product;
        this.stock = stock;
    }
}
