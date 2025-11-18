package com.hsfulda.demo.products.services;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class InventoryServiceImpl implements InventoryService {
    private HashMap<Integer,Integer> stock = new HashMap<Integer,Integer>();
    {
        stock.put(1,2);
        stock.put(2,5);
        stock.put(3,5);
        stock.put(4,5);
        stock.put(5,5);
    }

    public int getStockForProductId(int id) {
        return stock.getOrDefault(id,0);
    }

    public boolean reduceStockForProductId(int productId, int amount) {
        int currentStock = getStockForProductId(productId);

        if(currentStock <=0 || amount <=0) {
            return false;
        }

        if(currentStock-amount < 0) {
            return false;
        }

        stock.put(productId, currentStock - amount);
        return true;
    }
}
