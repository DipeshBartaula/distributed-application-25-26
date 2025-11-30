package com.hsfulda.demo.products.facade;

import com.hsfulda.demo.products.dto.ProductCatalogDTO;
import com.hsfulda.demo.products.dto.ProductDetailDTO;
import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCatalogFacade {
    @Autowired
    private ProductService productService;

    public ProductCatalogDTO getProductByColor(String color) {
        List<Product> productList = productService.getProductByColor(color);
        int size = productList.size();
        return new ProductCatalogDTO(productList, size);
    }

    public ProductCatalogDTO getProductList() {
        List<Product> product = productService.getProductList();
        int stock = product.size();
        return new ProductCatalogDTO(product, stock);
    }
}
