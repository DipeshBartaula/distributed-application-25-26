package com.hsfulda.demo.products.services;

public interface InventoryService {
    int getStockForProductId(Long id);
    boolean reduceStockForProductId(Long id, int amount);
}
