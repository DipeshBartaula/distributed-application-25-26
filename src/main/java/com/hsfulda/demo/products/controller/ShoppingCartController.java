package com.hsfulda.demo.products.controller;

import com.hsfulda.demo.products.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc-api/product")
public class ShoppingCartController {
    private final ShoppingCartService cartService;

    public ShoppingCartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "cart";
    }

    @GetMapping("/addcart/{id}")
    public String addToCart(@PathVariable int id) {
        cartService.addProduct(id);
        return "redirect:/mvc-api/product/cart";
    }
}
