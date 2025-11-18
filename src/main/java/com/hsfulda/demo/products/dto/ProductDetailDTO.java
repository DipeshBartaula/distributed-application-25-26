package com.hsfulda.demo.products.dto;

import com.hsfulda.demo.products.model.Product;

public class ProductDetailDTO {

    public Product product;
    public int stock;
    public boolean isSoldOut;

    public ProductDetailDTO(Product product, int stock) {
        this.product = product;
        this.stock = stock;
        this.isSoldOut = (stock <= 0);
    }
}
