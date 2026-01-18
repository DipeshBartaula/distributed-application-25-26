package com.hsfulda.demo.products.model;

import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import com.hsfulda.demo.products.model.Currency;
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
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    private boolean voucherApplied;

    @Autowired
    private PriceCalculationService priceCalculationService;

    public void setPriceCalculationService(PriceCalculationService priceCalculationService) {
        this.priceCalculationService = priceCalculationService;
    }

    public boolean isVoucherApplied() {
        return voucherApplied;
    }

    public void setVoucherApplied(boolean voucherApplied) {
        this.voucherApplied = voucherApplied;
    }

    public BigDecimal getOriginalTotalPrice() {
        BigDecimal total = products.entrySet().stream()
                .map(e -> e.getKey().getPrice().multiply(new BigDecimal(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (priceCalculationService != null) {
            // Apply currency conversion if current currency differs from default
            String defaultCurrency = priceCalculationService.getDefaultCurrency();
            if (currency != null && !currency.equals(defaultCurrency)) {
                total = priceCalculationService.convertCurrency(total,
                        Currency.valueOf(defaultCurrency),
                        Currency.valueOf(currency));
            } else {
                total = priceCalculationService.roundPrice(total);
            }
            return total;
        }
        return total.setScale(2, java.math.RoundingMode.HALF_UP);
    }

    /**
     * Calculates the total price of all products in the shopping cart.
     * 
     * Multiplies each product's price by its quantity and sums all values.
     * If a voucher is applied, the discount is deducted.
     * The final total is rounded to 2 decimal places (cents) using the
     * PriceCalculationService to ensure consistent monetary handling.
     * 
     * @return the total price as a BigDecimal rounded to 2 decimal places,
     *         or BigDecimal.ZERO if cart is empty
     */
    public BigDecimal getTotalPrice() {
        // Calculate original total first (which includes currency conversion)
        BigDecimal total = getOriginalTotalPrice();

        // Apply voucher if active
        // Note: Voucher percentage is applied to the already converted amount, which is
        // mathematically correct
        // (X * Conversion) * (1 - Discount) == (X * (1 - Discount)) * Conversion
        if (voucherApplied && priceCalculationService != null) {
            return priceCalculationService.applyVoucher(total);
        }

        return total;
    }
}
