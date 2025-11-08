package com.hsfulda.demo.products;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProductList();
    Product getProductById(int id);
    List<Product> getProductByColor(String color);
    List<Product> getProductByCategoryAndSize(String category, String size);
    List<Product> addNewProduct(Product product);

    List<Product> deleteProduct(Long id);

    Optional<Product> updateProduct(Product product);
}
