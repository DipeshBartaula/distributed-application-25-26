package com.hsfulda.demo.products.services;

import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart getCart();
    void addProduct(int id);
}
