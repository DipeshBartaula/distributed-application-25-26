package com.hsfulda.demo.products.services;

import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCart cart = new ShoppingCart();
    private final ProductService productService;

    @Autowired
    public ShoppingCartServiceImpl(ProductService productService, PriceCalculationService priceCalculationService) {
        this.productService = productService;
        this.cart.setPriceCalculationService(priceCalculationService);
        this.cart.setCurrency(priceCalculationService.getDefaultCurrency());
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void addProduct(Long id) {
        Product p = productService.getProductById(id);

        cart.products.put(p, cart.products.getOrDefault(p, 0) + 1);
    }

    @Override
    public void applyVoucher() {
        cart.setVoucherApplied(true);
    }

    @Override
    public void removeVoucher() {
        cart.setVoucherApplied(false);
    }

    @Override
    public void switchCurrency(String currencyCode) {
        try {
            // Validate currency code
            com.hsfulda.demo.products.model.Currency.valueOf(currencyCode);
            cart.setCurrency(currencyCode);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid currency code: " + currencyCode);
        }
    }
}
