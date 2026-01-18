package com.hsfulda.demo.products;

import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.model.ShoppingCart;
import com.hsfulda.demo.products.services.PriceCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartVoucherTest {

    private ShoppingCart cart;
    private PriceCalculationService priceCalculationService;

    @BeforeEach
    public void setUp() {
        cart = new ShoppingCart();
        // Mocking the Value injection by passing string directly
        priceCalculationService = new PriceCalculationService("EUR", new BigDecimal("10"));
        cart.setPriceCalculationService(priceCalculationService);
        cart.setCurrency(priceCalculationService.getDefaultCurrency());
    }

    @Test
    public void testCurrencyConfiguration() {
        assertEquals("EUR", cart.getCurrency());
    }

    @Test
    public void testOriginalPriceCalculation() {
        Product p1 = new Product();
        p1.setPrice(new BigDecimal("10.00"));
        cart.products.put(p1, 2); // 20.00

        Product p2 = new Product();
        p2.setPrice(new BigDecimal("5.50"));
        cart.products.put(p2, 1); // 5.50

        // Total: 25.50
        assertEquals(new BigDecimal("25.50"), cart.getOriginalTotalPrice());
        assertEquals(new BigDecimal("25.50"), cart.getTotalPrice());
    }

    @Test
    public void testVoucherApplication() {
        Product p1 = new Product();
        p1.setPrice(new BigDecimal("100.00"));
        cart.products.put(p1, 1);

        assertEquals(new BigDecimal("100.00"), cart.getTotalPrice());

        cart.setVoucherApplied(true);
        // 10% discount on 100.00 -> 90.00
        assertEquals(new BigDecimal("90.00"), cart.getTotalPrice());
        assertEquals(new BigDecimal("100.00"), cart.getOriginalTotalPrice()); // Original should remain same
    }

    @Test
    public void testVoucherRemoval() {
        Product p1 = new Product();
        p1.setPrice(new BigDecimal("50.00"));
        cart.products.put(p1, 1);

        cart.setVoucherApplied(true);
        assertEquals(new BigDecimal("45.00"), cart.getTotalPrice());

        cart.setVoucherApplied(false);
        assertEquals(new BigDecimal("50.00"), cart.getTotalPrice());
    }

    @Test
    public void testRoundingWithVoucher() {
        // Price: 19.99
        // 10% off -> 1.999 discount -> 17.991 final price
        // Rounded: 17.99

        Product p1 = new Product();
        p1.setPrice(new BigDecimal("19.99"));
        cart.products.put(p1, 1);

        cart.setVoucherApplied(true);
        assertEquals(new BigDecimal("17.99"), cart.getTotalPrice());
    }

    @Test
    public void testCurrencyConversion() {
        Product p1 = new Product();
        p1.setPrice(new BigDecimal("100.00"));
        cart.products.put(p1, 1);

        assertEquals("EUR", cart.getCurrency());
        assertEquals(new BigDecimal("100.00"), cart.getTotalPrice());

        // Switch to USD: 100 * 1.05 = 105.00
        cart.setCurrency("USD");
        assertEquals(new BigDecimal("105.00"), cart.getTotalPrice());
        assertEquals(new BigDecimal("105.00"), cart.getOriginalTotalPrice());

        // Switch back to EUR
        cart.setCurrency("EUR");
        assertEquals(new BigDecimal("100.00"), cart.getTotalPrice());
    }

    @Test
    public void testCurrencyConversionWithVoucher() {
        Product p1 = new Product();
        p1.setPrice(new BigDecimal("100.00"));
        cart.products.put(p1, 1);

        cart.setVoucherApplied(true); // 10% discount

        // EUR: 100 - 10% = 90
        assertEquals(new BigDecimal("90.00"), cart.getTotalPrice());

        // USD: 100 * 1.05 = 105
        // 105 - 10% = 105 - 10.5 = 94.50
        cart.setCurrency("USD");
        assertEquals(new BigDecimal("94.50"), cart.getTotalPrice());
    }
}
