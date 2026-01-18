package com.hsfulda.demo.products.services;

import com.hsfulda.demo.products.facade.OrderFacade;
import com.hsfulda.demo.products.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderAdapter {

    private final OrderFacade orderFacade;
    private final EMailService eMailService;

    public OrderAdapter(OrderFacade orderFacade, EMailService eMailService) {
        this.orderFacade = orderFacade;
        this.eMailService = eMailService;
    }

    public Order finalizeOrder(Double totalPrice) {
        // Adaptor: calls Facade to create order, then calls EmailService
        Order order = orderFacade.finalizeOrder(totalPrice);

        // Retrieve userId from the created order
        Long userId = order.getUserId();

        // Trigger Email
        eMailService.sendEMail(userId);

        return order;
    }
}
