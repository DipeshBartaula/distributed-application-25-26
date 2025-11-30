package com.hsfulda.demo.products.services;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class InventoryServiceImpl implements InventoryService {
    private HashMap<Integer,Integer> stock = new HashMap<Integer,Integer>();
    {
        stock.put(1,2);
        stock.put(2,4);
        stock.put(3,6);
        stock.put(4,5);
        stock.put(5,3);
    }

    public int getStockForProductId(Long id) {
        return stock.getOrDefault(id,0);
    }

    public boolean reduceStockForProductId(Long productId, int amount) {
        int currentStock = getStockForProductId(productId);

        if(currentStock <=0 || amount <=0) {
            return false;
        }

        if(currentStock-amount < 0) {
            return false;
        }

        stock.put(Math.toIntExact(productId), currentStock - amount);
        return true;
    }
}
