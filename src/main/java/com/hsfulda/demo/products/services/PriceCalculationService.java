package com.hsfulda.demo.products.services;

import com.hsfulda.demo.products.model.Currency;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Value;

/**
 * Service for handling price-related calculations and rounding operations.
 * 
 * This service provides reusable methods for price operations such as rounding
 * to ensure consistent monetary value handling across the application.
 * It encapsulates rounding logic to support future price-related operations
 * such as currency conversion, voucher application, and other monetary
 * calculations.
 */
@Service
public class PriceCalculationService {

    /** The standard scale for monetary values: 2 decimal places (cents) */
    private static final int MONETARY_SCALE = 2;

    /** Rounding mode: half-up (round from 0.5 and above) */
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    // Hardcoded conversion rate: 1 EUR = 1.05 USD
    private static final BigDecimal EUR_TO_USD_RATE = new BigDecimal("1.05");

    private final String defaultCurrency;
    private final BigDecimal voucherPercentage;

    public PriceCalculationService(
            @Value("${app.currency.default}") String defaultCurrency,
            @Value("${app.discount.percentage:10}") BigDecimal voucherPercentage) {
        this.defaultCurrency = defaultCurrency;
        this.voucherPercentage = voucherPercentage;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public BigDecimal getVoucherPercentage() {
        return voucherPercentage;
    }

    /**
     * Rounds a price to exactly 2 decimal places (cents) using half-up rounding.
     * 
     * This method ensures consistent price formatting for monetary values.
     * Rounding follows the half-up strategy: values >= 0.5 are rounded up,
     * values &lt; 0.5 are rounded down.
     * 
     * <p>
     * <strong>Examples:</strong>
     * </p>
     * <ul>
     * <li>19.994 → 19.99</li>
     * <li>19.995 → 20.00</li>
     * <li>19.996 → 20.00</li>
     * </ul>
     * 
     * @param price the price to round, must not be null
     * @return the rounded price with exactly 2 decimal places
     * @throws NullPointerException if price is null
     */
    public BigDecimal roundPrice(BigDecimal price) {
        if (price == null) {
            throw new NullPointerException("Price cannot be null");
        }
        return price.setScale(MONETARY_SCALE, ROUNDING_MODE);
    }

    /**
     * Converts an amount from one currency to another using a hardcoded exchange
     * rate.
     * 
     * @param amount       the amount to convert
     * @param fromCurrency the source currency
     * @param toCurrency   the target currency
     * @return the converted amount, rounded to 2 decimal places
     */
    public BigDecimal convertCurrency(BigDecimal amount, Currency fromCurrency, Currency toCurrency) {
        if (amount == null || fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        if (fromCurrency == toCurrency) {
            return roundPrice(amount);
        }

        BigDecimal rate;
        if (fromCurrency == Currency.EUR && toCurrency == Currency.USD) {
            rate = EUR_TO_USD_RATE;
        } else if (fromCurrency == Currency.USD && toCurrency == Currency.EUR) {
            // 1 / 1.05
            rate = BigDecimal.ONE.divide(EUR_TO_USD_RATE, 10, RoundingMode.HALF_UP);
        } else {
            // Should not happen with only two currencies, but good practice
            throw new UnsupportedOperationException("Conversion not supported for given currencies");
        }

        BigDecimal convertedAmount = amount.multiply(rate);
        return roundPrice(convertedAmount);
    }

    /**
     * Applies the configured percentage voucher to a price.
     *
     * @param price the original price
     * @return the discounted price, rounded to 2 decimal places
     */
    public BigDecimal applyVoucher(BigDecimal price) {
        return applyVoucher(price, this.voucherPercentage);
    }

    /**
     * Applies a percentage voucher to a price.
     * 
     * @param price      the original price
     * @param percentage the percentage to deduct (e.g. 10 for 10%)
     * @return the discounted price, rounded to 2 decimal places
     */
    public BigDecimal applyVoucher(BigDecimal price, BigDecimal percentage) {
        if (price == null || percentage == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        if (percentage.compareTo(BigDecimal.ZERO) < 0 || percentage.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }

        BigDecimal discountFactor = percentage.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);
        BigDecimal discountAmount = price.multiply(discountFactor);
        BigDecimal finalPrice = price.subtract(discountAmount);

        return roundPrice(finalPrice);
    }
}
