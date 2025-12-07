package com.hsfulda.demo.products.model;

import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import com.hsfulda.demo.products.services.PriceCalculationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Represents a shopping cart containing products and their quantities.
 * 
 * The shopping cart maintains a mapping of products to their quantities
 * and provides methods to calculate total price with proper rounding.
 */
public class ShoppingCart {
    public Map<Product, Integer> products = new HashMap<>();
    
    @Autowired
    private PriceCalculationService priceCalculationService;

    /**
     * Calculates the total price of all products in the shopping cart.
     * 
     * Multiplies each product's price by its quantity and sums all values.
     * The final total is rounded to 2 decimal places (cents) using the
     * PriceCalculationService to ensure consistent monetary handling.
     * 
     * @return the total price as a BigDecimal rounded to 2 decimal places,
     *         or BigDecimal.ZERO if cart is empty
     */
    public BigDecimal getTotalPrice() {
        BigDecimal total = products.entrySet().stream()
                .map(e -> e.getKey().getPrice().multiply(new BigDecimal(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // Round the final total to ensure consistent monetary representation
        if (priceCalculationService != null) {
            return priceCalculationService.roundPrice(total);
        }
        // Fallback if service is not injected (e.g., in tests)
        return total.setScale(2, java.math.RoundingMode.HALF_UP);
    }
}
