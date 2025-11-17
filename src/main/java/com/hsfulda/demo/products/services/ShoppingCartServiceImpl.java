package com.hsfulda.demo.products.services;

import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    private final ShoppingCart cart = new ShoppingCart();
    private final ProductService productService;

    public ShoppingCartServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void addProduct(int id) {
        Product p = productService.getProductById(id);

        cart.products.put(p, cart.products.getOrDefault(p,0) + 1);
    }
}
