package com.hsfulda.demo.products.services;

import com.hsfulda.demo.products.model.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    List<Product> getProductList();

    Product getProductById(Long id);

    List<Product> getProductByColor(String color);

    List<Product> getProductByCategoryAndSize(String category, String size);

    Optional<Product> addNewProduct(Product product);

    String deleteProduct(Long id);

    Optional<Product> updateProduct(Product product);

    Page<Product> getPaginatedProducts(Pageable pageable);

    List<Product> getProductsForTenant(String tenantId);
}
