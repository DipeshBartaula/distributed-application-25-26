package com.hsfulda.demo.products.controller;

import com.hsfulda.demo.products.dto.ProductDetailDTO;
import com.hsfulda.demo.products.facade.ProductDetailFacade;
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
    private ProductDetailFacade productDetailFacade;

    @GetMapping("/product/{id}")
    public String showProductDetail(@PathVariable Long id, Model model) {

        ProductDetailDTO dto = productDetailFacade.getProductDetailById(id);

        model.addAttribute("productDetailDTO", dto);
        return "product-detail";
    }
}
