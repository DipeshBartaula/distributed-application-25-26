package com.hsfulda.demo.products.services;

public interface InventoryService {
    int getStockForProductId(int id);
    boolean reduceStockForProductId(int id, int amount);
}
