package com.hsfulda.demo.products.facade;

import com.hsfulda.demo.products.dto.ProductDetailDTO;
import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.services.InventoryService;
import com.hsfulda.demo.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailFacade {
    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    public ProductDetailDTO getProductDetailById(int id) {
        Product product = productService.getProductById(id);
        int stock = inventoryService.getStockForProductId(id);
        return new ProductDetailDTO(product,stock);
    }
}
