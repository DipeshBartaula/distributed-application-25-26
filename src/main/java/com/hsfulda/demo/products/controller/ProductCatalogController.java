package com.hsfulda.demo.products.controller;

import com.hsfulda.demo.products.dto.ProductCatalogDTO;
import com.hsfulda.demo.products.dto.ProductDetailDTO;
import com.hsfulda.demo.products.facade.ProductCatalogFacade;
import com.hsfulda.demo.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mvc-api/product")
public class ProductCatalogController {
    @Autowired
    private ProductCatalogFacade productCatalogFacade;

    private final ProductService productService;

    public ProductCatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showCatalog(@RequestParam(required = false, defaultValue = "false") boolean edit,
                              Model model) {
        ProductCatalogDTO dto = productCatalogFacade.getProductList();
        model.addAttribute("productCatalogDTO", dto);
        model.addAttribute("editMode", edit);

        return "catalog";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {

        productService.deleteProduct((long) id);

        return "redirect:/mvc-api/product?edit=true";
    }

    @GetMapping("/color")
    public String productByColor(@RequestParam(required = false, defaultValue = "Red") String color, Model model) {
        ProductCatalogDTO dto = productCatalogFacade.getProductByColor(color);

        model.addAttribute("productCatalogDTO", dto);
        return "catalog";
    }
}

