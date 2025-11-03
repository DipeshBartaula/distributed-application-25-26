package com.hsfulda.demo.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    // Dependency injection via constructor
//    @Autowired
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    //Setter Injection
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getProductList();
    }

    //Creating endpoint product/{id}
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    //Get product by color
    @GetMapping("/product/color/{color}")
    public List<Product> getProductByColor(@PathVariable String color) {
        return productService.getProductByColor(color);
    }

    //Get product by category and size
    @GetMapping("/product/category/{category}/size/{size}")
    public List<Product> getProductByCategoryAndSize(@PathVariable String category, @PathVariable String size) {
        return productService.getProductByCategoryAndSize(category, size);
    }

    @PostMapping("/product")
    public List<Product> addProduct(@RequestParam int id,
                                    @RequestParam String name,
                                    @RequestParam double price,
                                    @RequestParam String size,
                                    @RequestParam String color,
                                    @RequestParam String category) {
        Product newProduct = new Product(id,name,price,size,color,category);
        return productService.addNewProduct(newProduct);
    }
}
