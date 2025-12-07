package com.hsfulda.demo.products.facade;

import com.hsfulda.demo.products.dto.ProductCatalogDTO;
import com.hsfulda.demo.products.dto.ProductDetailDTO;
import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Facade component for managing product catalog operations.
 * 
 * <h3>Dependencies:</h3>
 * <ul>
 *   <li><strong>ProductService</strong> - Provides access to the core product data and operations</li>
 * </ul>
 * 
 * <h3>Responsibilities:</h3>
 * This facade simplifies the controller layer by providing high-level methods for product catalog queries.
 * It aggregates product data from the ProductService and returns them as Data Transfer Objects (DTOs)
 * that are optimized for web presentation.
 */
@Service
public class ProductCatalogFacade {
    @Autowired
    private ProductService productService;

    /**
     * Retrieves all products filtered by the specified color.
     * 
     * @param color the color to filter products by
     * @return a ProductCatalogDTO containing the filtered product list and total count
     */
    public ProductCatalogDTO getProductByColor(String color) {
        List<Product> productList = productService.getProductByColor(color);
        int size = productList.size();
        return new ProductCatalogDTO(productList, size);
    }

    /**
     * Retrieves the complete list of all available products in the catalog.
     * 
     * @return a ProductCatalogDTO containing all products and the total product count
     */
    public ProductCatalogDTO getProductList() {
        List<Product> product = productService.getProductList();
        int stock = product.size();
        return new ProductCatalogDTO(product, stock);
    }
}
