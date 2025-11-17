package com.hsfulda.demo.products.controller;

import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc-api")
public class ProductDetailController {

    private final ProductService productService;

    public ProductDetailController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String showProductDetail(@PathVariable int id, Model model) {

        Product product = productService.getProductById(id);

        if (product == null) {
            return "product-not-found";
        }

        model.addAttribute("product", product);
        return "product-detail";
    }
}
