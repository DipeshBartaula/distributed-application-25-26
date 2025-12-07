package com.hsfulda.demo.products.facade;

import com.hsfulda.demo.products.dto.ProductDetailDTO;
import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.services.InventoryService;
import com.hsfulda.demo.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Facade component for managing detailed product information and inventory checks.
 * 
 * <h3>Dependencies:</h3>
 * <ul>
 *   <li><strong>ProductService</strong> - Provides product information and lookup capabilities</li>
 *   <li><strong>InventoryService</strong> - Manages stock levels and inventory data for products</li>
 * </ul>
 * 
 * <h3>Responsibilities:</h3>
 * This facade combines product details with inventory information to provide a complete view
 * of a product including its current stock status. It aggregates data from both services
 * and returns enriched product information through Data Transfer Objects (DTOs).
 */
@Service
public class ProductDetailFacade {
    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    /**
     * Retrieves complete details for a specific product including its current stock level.
     * 
     * Combines product information from ProductService with inventory data from InventoryService
     * to provide a comprehensive view of the product.
     * 
     * @param id the unique identifier of the product
     * @return a ProductDetailDTO containing product details and current stock information
     * @throws NoSuchElementException if no product with the given id exists
     */
    public ProductDetailDTO getProductDetailById(Long id) {
        Product product = productService.getProductById(id);
        int stock = inventoryService.getStockForProductId(id);
        return new ProductDetailDTO(product, stock);
    }


}
