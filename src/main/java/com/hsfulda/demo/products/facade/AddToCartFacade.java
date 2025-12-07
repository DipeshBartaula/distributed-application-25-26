package com.hsfulda.demo.products.facade;

import com.hsfulda.demo.products.model.ShoppingCart;
import com.hsfulda.demo.products.services.InventoryService;
import com.hsfulda.demo.products.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Facade component for managing shopping cart operations with inventory validation.
 * 
 * <h3>Dependencies:</h3>
 * <ul>
 *   <li><strong>ShoppingCartService</strong> - Manages shopping cart state and product additions</li>
 *   <li><strong>InventoryService</strong> - Handles inventory stock checks and reduction</li>
 * </ul>
 * 
 * <h3>Responsibilities:</h3>
 * This facade encapsulates the business logic for adding products to the shopping cart
 * with built-in inventory validation. It ensures products are only added if stock is available,
 * and atomically reduces inventory when a product is added to the cart.
 */
@Service
public class AddToCartFacade {
    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private InventoryService inventoryService;

    /**
     * Adds a product to the shopping cart if stock is available.
     * 
     * Checks inventory before adding the product to the cart and automatically reduces
     * the stock count by one when successfully added. If stock is unavailable, the product
     * is not added to the cart.
     * 
     * @param productId the unique identifier of the product to add to the cart
     */
    public void addToCart(Long productId) {
        int stock = inventoryService.getStockForProductId(productId);

        if (stock > 0) {
            inventoryService.reduceStockForProductId(productId,1);
            cartService.addProduct(productId);
        }
    }

    /**
     * Retrieves the current shopping cart.
     * 
     * Provides access to the shopping cart state without exposing the underlying CartService
     * to the controller layer.
     * 
     * @return the current ShoppingCart containing all added products
     */
    public ShoppingCart getCart() {
        return cartService.getCart();
    }
}
