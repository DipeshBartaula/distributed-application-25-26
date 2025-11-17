package com.hsfulda.demo.products.controller;

import com.hsfulda.demo.products.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mvc-api/product")
public class ProductCatalogController {

    private final ProductService productService;

    public ProductCatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showCatalog(@RequestParam(required = false, defaultValue = "false") boolean edit,
                              Model model) {

        model.addAttribute("products", productService.getProductList());
        model.addAttribute("editMode", edit);

        return "catalog";
    }

    // DELETE functionality using GET for this exercise
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {

        productService.deleteProduct((long) id);

        // redirect back to edit mode ON
        return "redirect:/mvc-api/product?edit=true";
    }
}

