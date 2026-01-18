package com.hsfulda.demo.products.services;

import com.hsfulda.demo.products.model.Order;
import com.hsfulda.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    @Autowired
    private UserService userService;

    public Order finalizeOrder(Double totalPrice, Long userId) {
        // In a real application, this would save the order to the database.
        return new Order(totalPrice, userId);
    }

    public Order finalizeOrderWithTotal(BigDecimal total) {
        long userId = userService.getUserId();
        return new Order(total.doubleValue(), userId);
    }

    public Order getRecentOrderForUser(String userId) {
        return new Order(0.0, Long.parseLong(userId));
    }
}
