package com.hsfulda.demo.products.controller;

import com.hsfulda.demo.products.facade.AddToCartFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc-api")
public class ShoppingCartController {
    @Autowired
    private AddToCartFacade addToCartFacade;

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", addToCartFacade.getCart());
        model.addAttribute("voucherPercentage", addToCartFacade.getVoucherPercentage());
        return "cart";
    }

    @GetMapping("/cart/voucher")
    public String applyVoucher() {
        addToCartFacade.applyVoucher();
        return "redirect:/mvc-api/cart";
    }

    @GetMapping("/cart/removevoucher")
    public String removeVoucher() {
        addToCartFacade.removeVoucher();
        return "redirect:/mvc-api/cart";
    }

    @GetMapping("/cart/currency")
    public String switchCurrency(@org.springframework.web.bind.annotation.RequestParam String code) {
        addToCartFacade.switchCurrency(code);
        return "redirect:/mvc-api/cart";
    }

    @GetMapping("/addcart/{id}")
    public String addToCart(@PathVariable Long id) {
        addToCartFacade.addToCart(id);
        return "redirect:/mvc-api/cart";
    }
}
