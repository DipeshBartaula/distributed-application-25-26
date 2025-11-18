package com.hsfulda.demo.products.controller;

import com.hsfulda.demo.products.dto.ProductDetailDTO;
import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.services.InventoryService;
import com.hsfulda.demo.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc-api")
public class ProductDetailController {

    @Autowired
    private InventoryService inventoryService;

    private final ProductService productService;

    public ProductDetailController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String showProductDetail(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        int stock = inventoryService.getStockForProductId(id);

        ProductDetailDTO dto = new ProductDetailDTO(product,stock);

        model.addAttribute("productDetailDTO", dto);
        return "product-detail";
    }
}
