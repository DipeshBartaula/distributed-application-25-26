package com.hsfulda.demo.products.controller;

import com.hsfulda.demo.products.model.Order;
import com.hsfulda.demo.products.services.OrderAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mvc-api")
public class OrderController {

    private final OrderAdapter orderAdapter;

    public OrderController(OrderAdapter orderAdapter) {
        this.orderAdapter = orderAdapter;
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam Double totalPrice, Model model) {
        Order order = orderAdapter.finalizeOrder(totalPrice);
        model.addAttribute("order", order);
        return "order-success";
    }
}
