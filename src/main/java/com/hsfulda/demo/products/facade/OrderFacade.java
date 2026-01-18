package com.hsfulda.demo.products.facade;

import com.hsfulda.demo.products.model.Order;
import com.hsfulda.demo.products.services.OrderService;
import com.hsfulda.demo.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class OrderFacade {

    private final OrderService orderService;
    private final UserService userService;

    public OrderFacade(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    public Order finalizeOrder(Double totalPrice) {
        // Adapter: fetches userId from separate UserService and passes it to
        // OrderService
        long userId = userService.getUserId();
        return orderService.finalizeOrder(totalPrice, userId);
    }
}
