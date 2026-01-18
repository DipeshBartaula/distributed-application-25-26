package com.hsfulda.demo.products.controller;

import com.hsfulda.demo.products.dto.ProductCatalogDTO;

import com.hsfulda.demo.products.facade.ProductCatalogFacade;
import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hsfulda.demo.products.model.Review;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

@Controller
@RequestMapping("/mvc-api")
public class ProductCatalogController {
    private final ProductCatalogFacade productCatalogFacade;
    private final ProductService productService;

    public ProductCatalogController(ProductCatalogFacade productCatalogFacade, ProductService productService) {
        this.productCatalogFacade = productCatalogFacade;
        this.productService = productService;
    }

    @GetMapping("/product")
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

    @GetMapping("/catalog-paginated")
    public String getPaginatedCatalog(@PageableDefault(size = 3) Pageable pageable, Model model) {
        Page<Product> productPage = productService.getPaginatedProducts(pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("page", productPage);

        return "catalog-paginated";
    }

    @PostMapping("/product/review")
    public String submitReview(Review review) {
        // For now just redirect back to the product page
        // In verify step we will print it or later use websocket to broadcast
        System.out.println("Received review: " + review.getReviewText() + " from " + review.getUserName()
                + " for product " + review.getProductName());
        return "redirect:/mvc-api/product/" + review.getProductId();
    }

    @MessageMapping("/review")
    @SendTo("/topic/reviews")
    public Review broadcastReview(Review review) {

        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss");
        review.setDate(now.format(formatter));

        return review;
    }
}
