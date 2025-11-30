package com.hsfulda.demo.products.facade;

import com.hsfulda.demo.products.model.ShoppingCart;
import com.hsfulda.demo.products.services.InventoryService;
import com.hsfulda.demo.products.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddToCartFacade {
    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private InventoryService inventoryService;

    public void addToCart(Long productId) {
        int stock = inventoryService.getStockForProductId(productId);

        if (stock > 0) {
            inventoryService.reduceStockForProductId(productId,1);
            cartService.addProduct(productId);
        }
    }

    // Proxy method to replace direct CartService access from controller
    public ShoppingCart getCart() {
        return cartService.getCart();
    }
}
