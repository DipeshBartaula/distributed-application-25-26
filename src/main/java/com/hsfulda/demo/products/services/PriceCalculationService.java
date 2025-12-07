package com.hsfulda.demo.products.services;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Service for handling price-related calculations and rounding operations.
 * 
 * This service provides reusable methods for price operations such as rounding
 * to ensure consistent monetary value handling across the application.
 * It encapsulates rounding logic to support future price-related operations
 * such as currency conversion, voucher application, and other monetary calculations.
 */
@Service
public class PriceCalculationService {
    
    /** The standard scale for monetary values: 2 decimal places (cents) */
    private static final int MONETARY_SCALE = 2;
    
    /** Rounding mode: half-up (round from 0.5 and above) */
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    /**
     * Rounds a price to exactly 2 decimal places (cents) using half-up rounding.
     * 
     * This method ensures consistent price formatting for monetary values.
     * Rounding follows the half-up strategy: values >= 0.5 are rounded up,
     * values < 0.5 are rounded down.
     * 
     * <p><strong>Examples:</strong></p>
     * <ul>
     *   <li>19.994 → 19.99</li>
     *   <li>19.995 → 20.00</li>
     *   <li>19.996 → 20.00</li>
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
}
